package com.assignment.node.operation;

import com.assignment.node.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SinglyLinkedList {
      static final Logger LOG = LoggerFactory.getLogger(SinglyLinkedList.class);
	public Node head;
	public int listCount;

	public SinglyLinkedList(){
		head = new Node(0);
		listCount = 0;
	}

	public void show(){
		Node current = head;
		while(current.next!=null){
			System.out.print(current.data+" -> ");
			current = current.next;
		}
		System.out.println(current.data);
	}

    public boolean add(int d){
    	Node end = new Node(d);
    	Node current = head;

    	while(current.next != null){
    		current = current.next;
    	}
    	current.next = end;
    	listCount++;
       LOG.info(d+" appended to tail!");
        return true;
    }

    public boolean add(int d,int index){
    	Node end = new Node(d);
    	Node current = head;
    	int jump;

    	if(index>listCount || index<1){
    		 LOG.error("Add Failed: index out of bounds of size of linked list!!");
    		return false;
    	}
    	else{
    		jump = 0;
    		while(jump<index-1){
    			current = current.next;
    			jump++;
    		}
    		end.next = current.next;
    		current.next = end;
    		listCount++;
    		 LOG.info("Success! "+d+" added at index "+index);
            return true;
    	}
    }

    public boolean deleteNodeWithData(int d){
    	Node current = head;
        while(current.next!=null){
            if(current.next.data==d){
                current.next = current.next.next;
                listCount--;
                LOG.info("Success! Node with data "+d+" deleted.");
                return true;
            }
            current = current.next;
        }
         LOG.info("Delete Failed: No node found with given data!");
        return false;
    }

    public boolean deleteNodeAtIndex(int index){
    	Node current = head;
    	int jump;
    	if(index>listCount || index<1){
    		 LOG.info("Delete Failed: index out of bounds of size of linked list!!");
    		return false;
    	}    	
    	else{
    		jump=0;
    		while(jump<index-1){
    			current = current.next;
    			jump++;
    		}
    		current.next = current.next.next;
    		 LOG.info("Success! Node at index "+index+" deleted.");
    		listCount--;
    		return true;
    	}
    }


}
