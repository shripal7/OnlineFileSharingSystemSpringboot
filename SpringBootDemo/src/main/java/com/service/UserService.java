package com.service;

import com.entity.CreateShareFolder;
import com.entity.User;
import com.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public File[] getAllUsers1(String userfolder) {
        File folder = new File("./"+userfolder);
        File[] listOfFiles = folder.listFiles();
       System.out.println("Userfolder is:"+userfolder);
        System.out.println("File list:"+listOfFiles);
        return listOfFiles;

    }


    public void addUser(User user) {
        userRepository.save(user);
        String folder = user.getEmail();
        new File("./" + folder).mkdir();


    }

    public List<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }



    public void createFolder(CreateShareFolder createShareFolder, String sharefolder){
          new File("./"+sharefolder + "/" + createShareFolder.getFoldername()).mkdir();
    }


    public boolean deletefile(String filename, String userFolder) {
        Path deleter = Paths.get("./" + userFolder + "/" + filename);
        System.out.println(deleter);

        try {
            if (Files.deleteIfExists(deleter)) {
                System.out.println("File deleted successfully");
                return true;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Internal Server Error");
            e.printStackTrace();
        }

        return false;
    }



    public void uploader(MultipartFile file, String userfolder) {

        try {

            byte[] bytes = file.getBytes();

            Path path = Paths.get("./" + userfolder + "/" + file.getOriginalFilename());
            Files.write(path, bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createShareFolder(CreateShareFolder createShareFolder, String sharefolder){

        new File("./"+sharefolder + "/" + createShareFolder.getFoldername()).mkdir();
        Path src = Paths.get("./"+sharefolder + "/" + createShareFolder.getFoldername());
        String[] shareinfo = createShareFolder.getEmails().split(",");
        for (String i : shareinfo) {
            Path dest = Paths.get("./" + i + "/" + createShareFolder.getFoldername());
            try {
                Files.createSymbolicLink(dest, src);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        }

}


