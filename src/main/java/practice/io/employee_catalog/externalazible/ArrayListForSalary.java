package practice.io.employee_catalog.externalazible;


import java.io.*;
import java.util.ArrayList;

public class ArrayListForSalary<E> extends ArrayList<EmployeeEx> implements Externalizable {
    private int salary;
    private int count;
    private EmployeeEx[] objects;
  

    public ArrayListForSalary() {
        super();
    }


    @Override
    public boolean add(EmployeeEx o) {
        salary += o.getSalary();
        boolean res = super.add(o);
        if (res) {
            count++;
        }
        return res;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Object[] object = super.toArray();
        out.writeInt(count);
        objects = new EmployeeEx[count];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = (EmployeeEx) object[i];
            out.writeInt(objects[i].getAge());
            out.writeInt(objects[i].getSalary());
            out.writeObject(objects[i].getName());
            out.writeObject(objects[i].getJob());
        }
        out.writeInt(salary);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        count = in.readInt();
        objects = new EmployeeEx[count];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = new EmployeeEx();
            objects[i].setAge(in.readInt());
            objects[i].setSalary(in.readInt());
            objects[i].setName((String) in.readObject());
            objects[i].setJob((String) in.readObject());
        }
        salary = in.readInt();
    }

    public int getSalary() {
        return salary;
    }

    public Object[] getObjects() {
        return objects;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public void setObjects(EmployeeEx[] objects) {
        this.objects = objects;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
