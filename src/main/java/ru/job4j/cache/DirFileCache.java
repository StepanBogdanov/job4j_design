package ru.job4j.cache;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder value = new StringBuilder();
        File file = new File(String.format("%s%s", cachingDir, key));
        try (FileReader reader = new FileReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {
                value.append((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value.toString();
    }

}
