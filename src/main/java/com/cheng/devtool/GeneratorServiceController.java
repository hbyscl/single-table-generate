package com.cheng.devtool;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月21日
 * @since soter 1.0.0
 */
public class GeneratorServiceController {
    public static void main(String[] args) throws Exception {
        String project = PropertiesUtil.getProperties("project");
        String entity = PropertiesUtil.getProperties("entity_package");

        File file = new File(project + "/" + entity.replace(".", "/"));
        File[] entityJavaFiles = file.listFiles((dir, name) -> !name.endsWith("Example.java"));
        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);
        List<Path> clzList = new ArrayList<>();
        for (File javaFile : entityJavaFiles) {
            Path clzFile = Paths.get(javaFile.getPath().replace(".java", ".class"));
            if(Files.exists(clzFile)){
                Files.delete(clzFile);
            }
            Thread.sleep(3000);
            Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(javaFile);
            JavaCompiler.CompilationTask cTask = jc.getTask(null, fileManager, null, null, null, fileObjects);
            cTask.call();

            clzList.add(clzFile);
        }
        fileManager.close();

        for (Path path : clzList) {
            try {
                URLClassLoader loader = new URLClassLoader(new URL[]{path.toUri().toURL()});
                Class<?> clz = loader.loadClass(path.getFileName().toString().replace(".class", ""));
                System.out.println("clz.getName() = " + clz.getName());
                loader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
