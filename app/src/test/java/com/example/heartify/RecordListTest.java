package com.example.heartify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.heartify.models.Record;
import com.example.heartify.models.RecordList;

import org.junit.Test;


public class RecordListTest {

    private RecordList mockRecordList(){
        RecordList recordList = RecordList.getInstance();
        recordList.addRecord(mockRecord());
        return recordList;
    }

    private Record mockRecord(){
        return new Record(120,80,60,"0:05","15-7-2022","");
    }

    @Test
    public void testAddRecord(){
        RecordList recordList = mockRecordList();
        assertEquals(1,recordList.getRecords().size());

        Record record  = new Record(125,78,60,"0:05","15-7-2022","");

        recordList.addRecord(record);

        assertEquals(2,recordList.getRecords().size());
        assertTrue(recordList.getRecords().contains(record));

        recordList.deleteAllRecords();
    }

    @Test
    public void testAddRecordException(){
        RecordList recordList = mockRecordList();
        assertEquals(1,recordList.getRecords().size());
        Record record = mockRecord();
        recordList.addRecord(record);
        assertThrows(IllegalArgumentException.class, () -> {
           recordList.addRecord(record);
        });
        recordList.deleteAllRecords();
    }

    @Test
    public void testGetRecords(){
        RecordList recordList = RecordList.getInstance();
        Record record  = new Record(125,78,60,"0:05","15-7-2022","");
        recordList.addRecord(record);

        assertEquals(1,recordList.getCount());
        assertTrue(recordList.getRecords().contains(record));
        assertEquals(0,recordList.getRecords().indexOf(record));
        recordList.deleteAllRecords();
    }

    @Test
    public void testGetSortedRecords(){
        RecordList recordList = RecordList.getInstance();
        Record record1  = new Record(125,78,60,"0:05","15-7-2022","");
        recordList.addRecord(record1);
        Record record2  = new Record(122,78,60,"0:05","15-7-2022","");
        recordList.addRecord(record2);

        assertEquals(0,recordList.getRecords(true).indexOf(record2));
        recordList.deleteAllRecords();
    }

    @Test
    public void testDeleteRecord(){
        RecordList recordList = mockRecordList();
        assertEquals(1,recordList.getCount());
        recordList.deleteRecord(0);
        assertEquals(0,recordList.getCount());
        assertThrows(IllegalArgumentException.class, () -> {
            recordList.deleteRecord(0);
        });
        recordList.deleteAllRecords();
    }

    @Test
    public void testUpdateRecord(){
        RecordList recordList = RecordList.getInstance();
        Record record  = new Record(125,78,60,"0:05","15-7-2022","");
        recordList.addRecord(record);
        assertEquals(1,recordList.getCount());
        Record updatedRecord =  new Record(124,76,60,"0:04","15-5-2022","");
        recordList.updateRecord(0,updatedRecord);
        assertEquals(0,recordList.getRecords().indexOf(updatedRecord));

        assertThrows(IllegalArgumentException.class, () -> {
            recordList.updateRecord(1,updatedRecord);
        });
        recordList.deleteAllRecords();
    }

}
