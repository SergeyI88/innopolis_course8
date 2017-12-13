package practice.io.employee_catalog.externalazible;




import java.io.*;
import java.util.*;

public class EmployeeManagerEx {
    private static final String path = "files/employeeE.bin";

    public static void main(String[] args) {
        EmployeeManagerEx manager = new EmployeeManagerEx();
        manager.saveOrUpdate(new EmployeeEx("igor2356", 24, 10050000, "worker"));
        manager.saveOrUpdate(new EmployeeEx("igor23", 24, 10, "worker"));
        manager.saveOrUpdate(new EmployeeEx("igor346", 11, 100, "worker"));
        manager.delete("igor23");
        manager.saveOrUpdate(new EmployeeEx("igor767", 23, 2000, "wor"));
        manager.show(manager.readObjects());
        System.out.println(manager.get("igor767"));
        System.out.println(manager.getByJob("worker"));
}

    public EmployeeManagerEx() {
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

    public void saveOrUpdate(EmployeeEx e) {
        ArrayListForSalary<EmployeeEx> list = readObjects();
        if (list.isEmpty()) {
            list = new ArrayListForSalary();
            list.add(e);
        } else {
            if (list.contains(e)) {
                list.add(list.indexOf(e), e);
            }
            list.add(e);
        }
        try {
            writeObjects(list);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void delete(String name) {
        ArrayListForSalary<EmployeeEx> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                list.remove(i);
                list.setCount(list.getCount() - 1);
                list.setSalary(list.getSalary() - list.get(i).getSalary());
            }
        }
        try {
            writeObjects(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EmployeeEx get(String name) {
        ArrayListForSalary<EmployeeEx> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayListForSalary<EmployeeEx> getByJob(String job) {
        ArrayListForSalary<EmployeeEx> inner = new ArrayListForSalary<>();
        ArrayListForSalary<EmployeeEx> list = readObjects();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getJob().equals(job)) {
                inner.add(list.get(i));
            }
        }
        return inner;
    }

    private ArrayListForSalary<EmployeeEx> readObjects() {
        ArrayListForSalary<EmployeeEx> list = new ArrayListForSalary<>();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
            list = (ArrayListForSalary<EmployeeEx>) in.readObject();
            if (!list.isEmpty()) {
                EmployeeEx[] employeeExes = (EmployeeEx[]) list.getObjects();
                HashSet<EmployeeEx> set = new HashSet<>();
                set.addAll(Arrays.asList(employeeExes));
                list.addAll((set));
                list.setCount(set.size());
                int salary = 0;
                for (EmployeeEx e: list) {
                    salary += e.getSalary();
                }
                list.setSalary(salary);

            }
            System.out.println(list.size());
        } catch (IOException e) {
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void show(ArrayListForSalary<EmployeeEx> list) {
        for (EmployeeEx e : list) {
            System.out.println(e);
        }
        System.out.println(list.getSalary());
    }

    private void writeObjects(ArrayListForSalary<EmployeeEx> listE) throws IOException {
        FileOutputStream fos = new FileOutputStream((path));
        ObjectOutputStream out = new ObjectOutputStream(fos);

        out.writeObject(listE);
        out.close();
    }
}

