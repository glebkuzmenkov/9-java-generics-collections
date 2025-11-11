package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final ArrayList<E> list = new ArrayList<>();
    private final File file;

    public SavedList(File file) {
        this.file = file;
        getData();
    }

    private void getData(){
        if (!file.exists()) {
            return;
        }
        try(ObjectInputStream objIS = new ObjectInputStream(Files.newInputStream(file.toPath()))){
            ArrayList<E> data = (ArrayList<E>) objIS.readObject();
            list.addAll(data);
        }catch (ClassNotFoundException | IOException e){
            throw new RuntimeException("Error in getting data from file!", e);
        }
    }

    private void saveData(){
        try(ObjectOutputStream objOS = new ObjectOutputStream(Files.newOutputStream(file.toPath()))){
            objOS.writeObject(list);
        }
        catch (IOException e){
            throw new RuntimeException("Error in setting data to file!", e);
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = list.set(index, element);
        saveData();
        return oldElement;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveData();
    }

    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        saveData();
        return removedElement;
    }
}
