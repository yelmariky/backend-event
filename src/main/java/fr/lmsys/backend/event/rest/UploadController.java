package fr.lmsys.backend.event.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.lmsys.backend.event.modele.Document;
import fr.lmsys.backend.event.modele.DocumentMetadata;
import fr.lmsys.backend.event.service.ArchiveService;
import fr.lmsys.backend.event.tools.FileUploadUtil;

@RestController
//@CrossOrigin
@RequestMapping("/api/upload")
public class UploadController {

	/*@Autowired
	StorageService storageService;
	*/
	@Autowired
	private  ArchiveService archiveService;

	//List<String> files = new ArrayList<String>();
	
	//@CrossOrigin
	@PostMapping("/user/{idUser}")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,@PathVariable(value = "idUser") String idUser) {
		String message = "";
		List<String> files = new ArrayList<String>();
		try {
			/**
           
			archiveService.save(document);
            return document.getMetadata();
			 */
			// check if image file
			/* String mimetype= new MimetypesFileTypeMap().getContentType(file.get
		        String type = mimetype.split("/")[0];
		        if(type.equals("image"))
		            System.out.println("It's an image");
		        else 
		            System.out.println("It's NOT an image");
		      */
			//check if images
			
			String contentType = file.getContentType();
			if (!FileUploadUtil.isValidImageFile(contentType)) {
				throw new Exception("Invalid image File! Content Type :-" + contentType);
			}
			
			
			Document document = new Document(file.getBytes(), file.getOriginalFilename(), LocalDateTime.now(), idUser);
			archiveService.save(document);
		//	storageService.store(file);
			files.add(file.getOriginalFilename());

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
		
	//@CrossOrigin
	@GetMapping("/getnbuploadfiles/user/{idUser}")
	public ResponseEntity<Integer> getNbreUploadFiles(Model model,@PathVariable(value = "idUser") String idUser) {
		List<DocumentMetadata> documents= archiveService.findDocumentsByPersonne(idUser);
		/*List<String> fileNames = documents
				.stream().map(document -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", document.getFileName()).build().toString())
				.collect(Collectors.toList());
				*/

		return ResponseEntity.ok().body(documents!=null?documents.size():0);
	}
	
	
	//@CrossOrigin
	@GetMapping("/getallfiles/user/{user}")
	public ResponseEntity<List<String>> getListFiles(@PathVariable(value = "user") String username) {
		List<DocumentMetadata> documents= archiveService.findDocumentsByPersonne(username);
		//List<String> docu
		List<String> fileNames = documents
				.stream().map(document -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", document.getIdUser(),document.getFileName()).build().toString())
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(fileNames);
	}
	

	@GetMapping("/user/{idUser}/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable(value = "idUser") String idUser,@PathVariable String filename) throws Exception {
		List<DocumentMetadata> documents= archiveService.findDocumentsByPersonne(idUser);
		/*List<String> fileNames = documents
				.stream().map(document -> MvcUriComponentsBuilder
						.fromMethodName(UploadController.class, "getFile", document.getFileName()).build().toString())
				.collect(Collectors.toList());
				*/
		DocumentMetadata doc = documents.stream().filter(docu -> docu.getFileName().equals(filename)).findAny().orElseThrow(() ->new Exception("Image with user "+ idUser+" not exist !!!!"));
		
		Resource file = archiveService.loadFile(doc.getUuid()+"/"+filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> nfeHandler(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
}
