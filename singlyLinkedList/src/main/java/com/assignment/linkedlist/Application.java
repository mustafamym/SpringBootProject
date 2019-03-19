package com.assignment.linkedlist;

import com.assignment.node.Node;
import com.assignment.node.operation.SinglyLinkedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("-----------------Start initialize ------------------");
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        log.info("-----------------add element to node ------------------");
        singlyLinkedList.add(1);
        singlyLinkedList.show();
        log.info("-----------------Succeed ------------------");
        log.info("-----------------add element to node ------------------");
        singlyLinkedList.add(2);
        singlyLinkedList.show();
        log.info("-----------------success ------------------");
        log.info("-----------------add element to node ------------------");
        singlyLinkedList.add(3);
        singlyLinkedList.show();
        log.info("-----------------Succeed ------------------");
        log.info("-----------------delete element from node ------------------");
        singlyLinkedList.deleteNodeWithData(2);
        singlyLinkedList.show();
        log.info("-----------------Succeed ------------------");
        log.info("-----------------delete element from node ------------------");
        singlyLinkedList.deleteNodeAtIndex(3);
        singlyLinkedList.show();
        log.info("-----------------Succeed ------------------");
        log.info("-----------------delete element from node ------------------");
        singlyLinkedList.deleteNodeAtIndex(1);
        singlyLinkedList.show();
        log.info("-----------------End operation ------------------");
        /*
      
      < OUTPUT >
      
      1 appended to tail!
      0 -> 1
      2 appended to tail!
      0 -> 1 -> 2
      3 appended to tail!
      0 -> 1 -> 2 -> 3
      Success! Node with data 2 deleted.
      0 -> 1 -> 3
      Delete Failed: index out of bounds of size of linked list!!
      0 -> 1 -> 3
      Success! Node at index 1 deleted.
      0 -> 3
      
      <end of OUTPUT>
      
         */
    }
}
