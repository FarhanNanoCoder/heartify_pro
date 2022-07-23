package com.example.heartify.models;

import com.example.heartify.utils.RecordComparatorOnSystolicPressure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of Record objects
 * This uses singleton approach to ensure data consistency
 */
public class RecordList {
    private static RecordList recordList_instance = null;
    private List<Record> records = new ArrayList<>();
    private RecordList(){}

    /**
     * This is a method to ensure single instance of the class
     */
    public static RecordList getInstance(){
        if(recordList_instance==null){
            recordList_instance = new RecordList();
        }
        return recordList_instance;
    }

    /**
     * This is a method to get the list of records
     */
    public List<Record> getRecords() {
        return records;
    }

    public List<Record> getRecords(boolean sortBySystolicPressure){
        if(sortBySystolicPressure){
            Collections.sort(records, new RecordComparatorOnSystolicPressure());
        }
        return records;
    }

    /**
     * This is a method to get a particular record from the  list of records
     */
    public Record getRecord(int position) {
        if(position>=0 && position<records.size()){
            return records.get(position);
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This is a method to get the count of records
     */
    public int getCount() {
        return records.size();
    }

    /**
     * This is a method to set the list of records
     * @param records
     */
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    /**
     * This is a method to add a record to the list of records
     * @param record
     */
    public void addRecord(Record record){
        if(records.contains(record)){
            throw new IllegalArgumentException();
        }else{
            records.add(record);
        }
    }

    /**
     * This is a method to update a particular record
     * @param record, position
     */
    public void updateRecord(int position,Record record){
        if(records.size()>position && position>=0){
            records.set(position,record);
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * This is a method to delete a particular record
     * @param position
     */
    public void  deleteRecord(int position){
        if(records.size()>position && position>=0){
            records.remove(position);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void deleteAllRecords(){
        records.clear();
    }
}
