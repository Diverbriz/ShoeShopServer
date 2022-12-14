package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sheloumov.v.d.ShoeShop.entity.FileEntity;
import sheloumov.v.d.ShoeShop.entity.ItemImage;
import sheloumov.v.d.ShoeShop.repository.FileRepository;
import sheloumov.v.d.ShoeShop.repository.ItemImageRepository;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final FileRepository fileRepository;


//    private String path = "src/main/resources/static";
    Path path = Paths.get("src/main/resources/static");
    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        ItemImage itemImage = new ItemImage();
//
//        itemImage.setUrl();
//        if()
//    }

    public void save(MultipartFile file
//                     Item itemId
    ) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());
        write(file, path);

        System.out.println(StringUtils.cleanPath(file.getOriginalFilename()) + "\n\n\n");
        fileRepository.save(fileEntity);
    }

    public void write(MultipartFile file, Path dir) throws IOException {
        Path filepath = Paths.get(dir.toString(), file.getOriginalFilename());

        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        }
    }
    public void multipartFileToFile(
            MultipartFile multipart,
            Path dir
    ) throws IOException {
        Path filepath = Paths.get(dir.toString(), multipart.getOriginalFilename());
        multipart.transferTo(filepath);
    }

    public Optional<FileEntity> getFile(String id) {

        return fileRepository.findById(id);
    }



    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}
