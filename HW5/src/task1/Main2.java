package task1;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main2 {

    private static final String AUDIO_ZIP = "C:\\1\\audio.zip";
    private static final String VIDEO_ZIP = "C:\\1\\video.zip";
    private static final String IMAGE_ZIP = "C:\\1\\image.zip";

    public static void main(String[] args) throws IOException {
        String root = "C:\\1\\folder1";

        File rootDir = new File(root);

        List<File> audio = new ArrayList<>();
        List<File> video = new ArrayList<>();
        List<File> image = new ArrayList<>();

        Map<String, List<File>> filesStorage = new HashMap<>();

        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, rootDir.listFiles());

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
            } else if (currentFile.getName().matches("(?i).*\\.(jpeg|jpg|gif|tiff|png)")) {
                image.add(currentFile);
            } else if (currentFile.getName().matches("(?i).*\\.(avi|mp4|flv)")) {
                video.add(currentFile);
            } else if (currentFile.getName().matches("(?i).*\\.(mp3|wav|wm)")) {
                audio.add(currentFile);
            }
        }

        filesStorage.put(AUDIO_ZIP, audio);
        filesStorage.put(IMAGE_ZIP, image);
        filesStorage.put(VIDEO_ZIP, video);

        for (Map.Entry<String, List<File>> entry : filesStorage.entrySet()) {
            archive(entry.getKey(), entry.getValue());
        }
    }

    private static void archive(String directory, List<File> files) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(directory);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream)) {

            for (File file : files) {
                System.out.println("Writing file : " + file.getName());
                try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getPath()));
                    int i;
                    while ((i = reader.read()) != -1) {
                        bufferedOutputStream.write(i);
                    }
                    bufferedOutputStream.flush();
                }
            }
        }
    }
}