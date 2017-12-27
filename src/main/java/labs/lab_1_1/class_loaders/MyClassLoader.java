package labs.lab_1_1.class_loaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.equals("labs.lab_1_1.Reader")) {
            try {
                String destination = "file:C:/projects/TestForClassLoaders/count_words/Reader.class";
                URL url = new URL(destination);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                int data = inputStream.read();
                while (data != -1) {
                    baos.write(data);
                    data = inputStream.read();
                }
                inputStream.close();

                byte[] classData = baos.toByteArray();

                return defineClass(name, classData, 0, classData.length);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        } else if (name.equals("labs.lab_1_1.Counter")) {
            try {
                String destination = "file:C:/projects/TestForClassLoaders/count_words/Counter.class";
                URL url = new URL(destination);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                int data = inputStream.read();
                while (data != -1) {
                    baos.write(data);
                    data = inputStream.read();
                }
                inputStream.close();

                byte[] classData = baos.toByteArray();

                return defineClass(name, classData, 0, classData.length);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return super.loadClass(name);
        }
    }
}
