/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.assignment.node.operation.SinglyLinkedList;
import org.junit.Test;


public class TestRunner {

   @Test
    public void testSinglyLinkedList() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
    	singlyLinkedList.add(1);
    	singlyLinkedList.show();
    	singlyLinkedList.add(2);
    	singlyLinkedList.show();
    	singlyLinkedList.add(3);
    	singlyLinkedList.show();
    	singlyLinkedList.deleteNodeWithData(2);
    	singlyLinkedList.show();
    	singlyLinkedList.deleteNodeAtIndex(3);
    	singlyLinkedList.show();
    	singlyLinkedList.deleteNodeAtIndex(1);
    	singlyLinkedList.show();
    }
}