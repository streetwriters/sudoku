package com.streetwriters.sudoku.Functions.FetchData;

import com.streetwriters.sudoku.Functions.Utils.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import test1.sudukoSolvedRiddle;

public class Files {

    public Object load(String FileName) throws Exception {
        File path = MyApplication.getContext().getFilesDir();
        File file = new File(path, FileName);
        InputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object =  ois.readObject();
        ois.close();
        return object;
    }

    public Object loadAssets(String fileName) throws Exception {
        InputStream fis = MyApplication.getContext().getAssets().open(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object =  ois.readObject();
        ois.close();
        return object;
    }



    public void save(Object object,String FileName) throws Exception {
        File path = MyApplication.getContext().getFilesDir();
        File file = new File(path, FileName);
        OutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
    }

    public ArrayList<sudukoSolvedRiddle> getAssetFiles(String FileName) throws Exception {
        InputStream fis = MyApplication.getContext().getAssets().open(FileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<sudukoSolvedRiddle> riddle1 = (ArrayList<sudukoSolvedRiddle>) ois.readObject();
        ois.close();
        return riddle1;
    }

    public void delete(String filename){
            File path = MyApplication.getContext().getFilesDir();
            File file = new File(path, filename);
            file.delete();
    }

}
