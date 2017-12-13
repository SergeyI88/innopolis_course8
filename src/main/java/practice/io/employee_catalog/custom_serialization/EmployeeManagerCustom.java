package practice.io.employee_catalog.custom_serialization;


import java.io.*;
import java.util.ArrayList;

public class EmployeeManagerCustom {
    private static final String path = "files/employeeCustom.bin";
    public int allSalary;

    public static void main(String[] args) {
        EmployeeManagerCustom manager = new EmployeeManagerCustom();
        manager.saveOrUpdate(new EmployeeCustom("igor2356", 24, 10050000, "worker"));
        manager.saveOrUpdate(new EmployeeCustom("igor23", 24, 10, "worker"));
        manager.saveOrUpdate(new EmployeeCustom("igor346", 11, 100, "worker"));
        manager.show(manager.readObjects());
        manager.delete("igor23");
        manager.saveOrUpdate(new EmployeeCustom("igor767", 23, 2000, "wor"));
        manager.show(manager.readObjects());
        System.out.println(manager.get("igor767"));
        System.out.println(manager.getByJob("worker"));
        System.out.println(manager.allSalary);
    }

    public EmployeeManagerCustom() {
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

    public void saveOrUpdate(EmployeeCustom e) {
        ArrayList<EmployeeCustom> list = readObjects();
        if (list.isEmpty()) {
            list = new ArrayList();
            list.add(e);
            allSalary += e.getSalary();
        } else {
            if (list.contains(e)) {
                allSalary -= list.get(list.indexOf(e)).getSalary();
                list.add(list.indexOf(e), e);
                allSalary += e.getSalary();
            } else {
                list.add(e);
                allSalary += e.getSalary();
            }
        }
        try {
            writeObjects(list);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void delete(String name) {
        ArrayList<EmployeeCustom> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                allSalary -= list.get(i).getSalary();
                list.remove(i);
            }
        }
        try {
            writeObjects(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmployeeCustom get(String name) {
        ArrayList<EmployeeCustom> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<EmployeeCustom> getByJob(String job) {
        ArrayList<EmployeeCustom> inner = new ArrayList<>();
        ArrayList<EmployeeCustom> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getJob().equals(job)) {
                inner.add(list.get(i));
            }
        }
        return inner;
    }

    private ArrayList<EmployeeCustom> readObjects() {
        ArrayList<EmployeeCustom> list = new ArrayList<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            list = (ArrayList<EmployeeCustom>) in.readObject();
            allSalary = in.readInt();
        } catch (IOException e) {
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void show(ArrayList<EmployeeCustom> list) {
        for (EmployeeCustom e : list) {
            System.out.println(e);
        }
    }

    private void writeObjects(ArrayList<EmployeeCustom> listE) throws IOException {
        FileOutputStream fos = new FileOutputStream((path));
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(listE);
        out.writeInt(allSalary);
        out.close();
    }

}

