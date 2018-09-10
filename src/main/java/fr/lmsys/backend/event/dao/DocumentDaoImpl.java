package fr.lmsys.backend.event.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import fr.lmsys.backend.event.modele.Document;
import fr.lmsys.backend.event.modele.DocumentMetadata;

/**
 * Data access object to insert, find and load {@link Document}s.
 * 
 * FileSystemDocumentDao saves documents in the file system. No database in
 * involved. For each document a folder is created. The folder contains the
 * document and a properties files with the meta data of the document. Each
 * document in the archive has a Universally Unique Identifier (UUID). The name
 * of the documents folder is the UUID of the document.
 * 
 * @author Daniel Murygin <daniel.murygin[at]gmail[dot]com>
 */
//@PropertySource("config-upload.properties")
@Service("documentDao")
public class DocumentDaoImpl implements IDocumentDao {

	@Value("${archive}")
	String archive;
	private static final Logger LOG = LoggerFactory.getLogger(DocumentDaoImpl.class);

	// public static final String DIRECTORY = "archive";
	public static final String META_DATA_FILE_NAME = "metadata.properties";

	@PostConstruct
	public void init() {
		createDirectory(archive);
	}

	/**
	 * Inserts a document to the archive by creating a folder with the UUID of
	 * the document. In the folder the document is saved and a properties file
	 * with the meta data of the document.
	 * 
	 * @see org.murygin.archive.dao.IDocumentDao#insert(org.murygin.archive.service.Document)
	 */
	@Override
	public void insert(Document document) {
		try {
			// document.get
			createDirectory(document);
			saveFileData(document);
			saveMetaData(document);
		} catch (IOException e) {
			String message = "Error while inserting document";
			LOG.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Finds documents in the data store matching the given parameter. To find a
	 * document all document meta data sets are iterated to check if they match
	 * the parameter.
	 * 
	 * @see org.murygin.archive.dao.IDocumentDao#findByPersonNameDate(java.lang.String,
	 *      java.util.Date)
	 */
	@Override
	public List<DocumentMetadata> findByPersonNameDate(String personName, Date date) {
		try {
			return findInFileSystem(personName, date);
		} catch (IOException e) {
			String message = "Error while finding document, person name: " + personName + ", date:" + date;
			LOG.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Returns the document from the data store with the given UUID.
	 * 
	 * @see org.murygin.archive.dao.IDocumentDao#load(java.lang.String)
	 */
	@Override
	public Document load(String uuid) {
		try {
			return loadFromFileSystem(uuid);
		} catch (IOException e) {
			String message = "Error while loading document with id: " + uuid;
			LOG.error(message, e);
			throw new RuntimeException(message, e);
		}

	}

	private List<DocumentMetadata> findInFileSystem(String personName, Date date) throws IOException {
		List<String> uuidList = getUuidList();
		List<DocumentMetadata> metadataList = new ArrayList<DocumentMetadata>(uuidList.size());
		for (String uuid : uuidList) {
			DocumentMetadata metadata = loadMetadataFromFileSystem(uuid);
			if (isMatched(metadata, personName, date)) {
				metadataList.add(metadata);
			}
		}
		return metadataList;
	}

	private boolean isMatched(DocumentMetadata metadata, String personName, Date date) {
		if (metadata == null) {
			return false;
		}
		boolean match = true;
		if (personName != null) {
			match = (personName.equals(String.valueOf(metadata.getIdUser())));
		}
		if (match && date != null) {
			match = (date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
					.equals(metadata.getDocumentDate()));
		}
		return match;
	}

	private DocumentMetadata loadMetadataFromFileSystem(String uuid) throws IOException {
		DocumentMetadata document = null;
		String dirPath = getDirectoryPath(uuid);
		File file = new File(dirPath);
		if (file.exists()) {
			Properties properties = readProperties(uuid);
			document = new DocumentMetadata(properties);

		}
		return document;
	}

	private Document loadFromFileSystem(String uuid) throws IOException {
		DocumentMetadata metadata = loadMetadataFromFileSystem(uuid);
		if (metadata == null) {
			return null;
		}
		Path path = Paths.get(getFilePath(metadata));
		Document document = new Document(metadata);
		document.setFileData(Files.readAllBytes(path));
		return document;
	}

	private String getFilePath(DocumentMetadata metadata) {
		String dirPath = getDirectoryPath(metadata.getUuid());
		StringBuilder sb = new StringBuilder();
		sb.append(dirPath).append(File.separator).append(metadata.getFileName());
		return sb.toString();
	}

	private void saveFileData(Document document) throws IOException {
		String path = getDirectoryPath(document);
		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(new File(new File(path), document.getFileName())));

			stream.write(document.getFileData());
		} finally {
			stream.close();

		}
	}

	public void saveMetaData(Document document) throws IOException {
		String path = getDirectoryPath(document);
		Properties props = document.createProperties();
		File f = new File(new File(path), META_DATA_FILE_NAME);
		OutputStream out = new FileOutputStream(f);
		props.store(out, "Document meta data");
	}

	private List<String> getUuidList() {
		File file = new File(archive);
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		return Arrays.asList(directories);
	}

	private Properties readProperties(String uuid) throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(new File(getDirectoryPath(uuid), META_DATA_FILE_NAME));
			prop.load(input);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	private String createDirectory(Document document) {
		String path = getDirectoryPath(document);
		createDirectory(path);
		return path;
	}

	private String getDirectoryPath(Document document) {
		return getDirectoryPath(document.getUuid());
	}

	private String getDirectoryPath(String uuid) {
		StringBuilder sb = new StringBuilder();
		sb.append(archive).append(File.separator).append(uuid);
		String path = sb.toString();
		return path;
	}

	private void createDirectory(String path) {
		File file = new File(path);

		file.mkdirs();
	}

	@Override
	public List<DocumentMetadata> findByPersonName(String personName) {
		try {
			return findInFileSystem(personName, null);
		} catch (IOException e) {
			String message = "Error while finding document, person name: " + personName;
			LOG.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/*
	 * @Override public List<DocumentMetadata>
	 * findByPersonNameImageLaPlusRecente(String personName) { return
	 * Collections.sort(findInFileSystem(personName,null),new
	 * Comparator<DocumentMetadata>() {
	 * 
	 * @Override public int compare(DocumentMetadata d1, DocumentMetadata d2){
	 * return 0; } });
	 * 
	 * }
	 **/

	/*
	 * public void store(MultipartFile file) { try {
	 * Files.copy(file.getInputStream(),
	 * this.rootLocation.resolve(file.getOriginalFilename())); } catch
	 * (Exception e) { throw new RuntimeException("FAIL!"); } }
	 */
	public Resource loadFile(String filename) {
		try {
			// private final Path rootLocation = Paths.get(archive);
			Path file = Paths.get(archive).resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	/*
	 * public void deleteAll() {
	 * FileSystemUtils.deleteRecursively(rootLocation.toFile()); }
	 */
	/*
	 * public void init() { try { if (!Files.exists(rootLocation)){
	 * Files.createDirectory(rootLocation); } } catch (IOException e) { throw
	 * new RuntimeException("Could not initialize storage!"); } }
	 */
}
