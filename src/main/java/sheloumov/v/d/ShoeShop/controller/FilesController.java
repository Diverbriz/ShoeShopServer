package sheloumov.v.d.ShoeShop.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import sheloumov.v.d.ShoeShop.entity.ItemImage;

import sheloumov.v.d.ShoeShop.service.FileService;
import sheloumov.v.d.ShoeShop.service.ItemImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("api/files")
public class FilesController {

    @Value("${upload.file}")
    private String uploadPath;

    private final ItemImageService itemImageService;
    private final FileService fileService;

    @Autowired
    public FilesController(ItemImageService itemImageService, FileService fileService) {
        this.itemImageService = itemImageService;
        this.fileService = fileService;
    }


    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file
    ) {
        try {
            if(file != null){
                File uploadDir = new File(uploadPath);
                System.out.println(uploadDir.getAbsolutePath());
                itemImageService.createItemImage(file);
            }


            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", e.getMessage()));
        }
    }

    @GetMapping()
    public List<ItemImage> getAllItemImage() {
        return itemImageService.getAllItemImage();
    }




//    @GetMapping(path = "/{item_id}")
//    public ResponseEntity<List<ItemImage>> getItemImageByItemId(@PathVariable Long item_id) {
//
//        return new ResponseEntity<>(itemImageService.getItemImageByItemId(item_id), HttpStatus.OK);
////        return ResponseEntity.ok()
////                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
////                .contentType(MediaType.valueOf(fileEntity.getContentType()))
////                .body(fileEntity.getData());
//    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImageDynamicType(@PathVariable Long id) throws IOException {

            ItemImage itemImage = itemImageService.findFirstById(id);
            System.out.println(itemImage.getUrl());
                if(itemImage.getUrl() != null){
                    File file = new File(uploadPath+"/" + itemImage.getUrl());
                    InputStream in = new FileInputStream(file.getAbsolutePath());
                    return IOUtils.toByteArray(in);
                }
        return new byte[0];
    }

    @PostMapping("/upload")
    public ResponseEntity<ItemImage> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        return new ResponseEntity<>(itemImageService.createItemImage(file), HttpStatus.OK);
    }

    @GetMapping(value = "/classpath")
    public void fromClasspathAsHttpServResp(HttpServletResponse response) throws IOException {
        String resourceLocation ;
        ClassPathResource imageFile = new ClassPathResource(uploadPath + "/" + "nike-metcon-5-black.png");

        StreamUtils.copy(imageFile.getInputStream(), response.getOutputStream());
    }
}
