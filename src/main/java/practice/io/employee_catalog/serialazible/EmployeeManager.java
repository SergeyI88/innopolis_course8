package practice.io.employee_catalog.serialazible;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements Serializable {
    private static final String path = "files/employeeS.bin";

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();

        manager.saveOrUpdate(new Employee("igor2356", 24, 10, "worker"));
        manager.saveOrUpdate(new Employee("igor23", 24, 10, "worker"));
        manager.saveOrUpdate(new Employee("igor346", 11, 100, "worker"));
        manager.delete("igor");
        manager.saveOrUpdate(new Employee("igor767", 23, 2000, "worker"));
        manager.show(manager.readObjects());
}

    public EmployeeManager() {
        if (!new File(path).exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.close(); // this is need for write first header otherwise when we will read throw EOFException
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveOrUpdate(Employee e) {
        List<Employee> list = readObjects();
        if (list.isEmpty()) {
            list = new ArrayList<>();
            list.add(e);
        } else {
            boolean res = true;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(e)) {
                    list.set(list.indexOf(list.get(i)), e);
                    System.out.println("inside: " + e);
                    res = false;
                } else if (i == (list.size() - 1) && res) {
                    list.add(e);
                }
            }
        }
        try {
            writeObjects(list);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void delete(String name) {
        List<Employee> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                list.remove(i);
            }
        }
        try {
            writeObjects(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee get(String name) {
        List<Employee> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public List<Employee> getByJob(String job) {
        ArrayList<Employee> inner = new ArrayList<>();
        List<Employee> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getJob().equals(job)) {
                inner.add(list.get(i));
            }
        }
        return inner;
    }

    private List<Employee> readObjects() {
        List<Employee> list = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            Employee e = new Employee();
            while (e != null) {
                e = (Employee) in.readObject();
               // System.out.println(e);
                list.add(e);
            }
        } catch (IOException e) {
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void show(List<Employee> list) {
        for (Employee e : list) {
            System.out.println(e);
        }
    }

    private void writeObjects(List<Employee> listE) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        for (Employee e : listE) {
            out.writeObject(e);
        }
        out.close();
    }

    private static class AppendingObjectOutputStream extends ObjectOutputStream {
        public AppendingObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }

}

