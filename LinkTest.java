/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linktest;

/**
 *
 * @author Isamar
 */
/* This program is designed to manage a list of items. It is able to add items at
 the end or beginning of a list, to delete items, to give the length of the list or
 to reverse the list. It also includes a set of regulations in case one encounters an 
 empty list.
 */
public class LinkTest {

    //Here the interface with the added methods: append and reverse.

    public interface DoublyLinkedList {

        DoublyLinkedList prepend(String info);

        void append(String info);

        DoublyLinkedList delete(int index);

        int length();

        @Override
        String toString();

        DoublyLinkedList reverse();

    }
    /*The beginning of the class which inlcudes the two constructors, the gets and 
     sets and the methods to be used. */

    public static class DoublyLinkedStringList implements DoublyLinkedList {

        private DoublyLinkedStringList next;
        private DoublyLinkedStringList prev;
        private final String value;

        public DoublyLinkedStringList getNext() {
            return next;
        }

        public DoublyLinkedStringList getPrev() {
            return prev;
        }

        public String getValue() {
            return value;
        }

        public void setNext(DoublyLinkedStringList N) {
            next = N;
        }

        public void setPrev(DoublyLinkedStringList P) {
            prev = P;
        }

        //Constructor #1 with a String, and next and prev set to null.

        public DoublyLinkedStringList(String input) {
            value = input;
            next = null;
            prev = null;
        }

        //Constructor #2 

        private DoublyLinkedStringList(DoublyLinkedStringList N, DoublyLinkedStringList P, String input) {
            value = input;
            next = N;
            prev = P;
        }

        //Method #1: adds items to the beginning of the list.

        @Override
        public DoublyLinkedStringList prepend(String info) {

            DoublyLinkedStringList newnode = new DoublyLinkedStringList(this, null, info);
            this.setPrev(newnode);
            return newnode;
        }

        //Method #2: adds items to the end of the list.

        @Override
        public void append(String info) {

            DoublyLinkedStringList i = this;
            while (i.getNext() != null) {
                i = i.getNext();
            }
            DoublyLinkedStringList newnode = new DoublyLinkedStringList(null, i, info);
            i.setNext(newnode);
        }

        //Method #3: Deletes any desired item of the list.

        @Override
        public DoublyLinkedStringList delete(int index) {

            DoublyLinkedStringList i = this;
            int counter = 0;
            int len = this.length() - 1;
            if (index == 0) {
                DoublyLinkedStringList newhead = this.getNext();
                return newhead;
            } else if (index > 0 && index < len) {
                while (counter < index) {
                    i = i.getNext();
                    counter += 1;
                }
                i.getPrev().setNext(i.getNext());
                i.getNext().setPrev(i.getPrev());
                return this;
            } else if (index == len) {
                while (counter < index) {
                    i = i.getNext();
                    counter += 1;
                }
                DoublyLinkedStringList newtail = i.getPrev();
                newtail.setNext(null);
                return this;
            } else {
                System.out.println("Not a valid index");
                return this;
            }
        }

        @Override
        // Method #4: Gives the number of items there are currently on the list.
        public int length() {
            DoublyLinkedStringList i = this;
            int counter = 1;
            while (i.getNext() != null) {
                i = i.getNext();
                counter += 1;
            }
            return counter;
        }

        //Method #5: Shows the actual items and organizes them with comma-separation.

        @Override
        public String toString() {

            DoublyLinkedStringList i = this;
            String totalValue = "";
            while (i.getNext() != null) {
                totalValue += (i.getValue() + ", ");
                i = i.getNext();
            }
            totalValue += (i.getValue());
            return totalValue;
        }

        //Method #6: Reverses the list 
        @Override
        public DoublyLinkedStringList reverse() {
            DoublyLinkedStringList i = this;
            DoublyLinkedStringList P = new DoublyLinkedStringList("");
            while (i != null) {
                DoublyLinkedStringList N = i.getNext();
                i.setNext(i.getPrev());
                i.setPrev(N);
                P = i;
                i = N;
            }
            return P;
        }
    }
//In the main we have "list" to be handled as our list and a series of examples.

    public static void main(String[] args) {

        DLinkedStringList list = new DLinkedStringList("sold");
        list.append("his soul");
        list.prepend("devil worshipper");
        list.prepend("The dyslexic");
        list.append("to Santa.");
        System.out.println(list);
        list.delete(0);
        list.delete(2);
        System.out.println(list);
        list.delete(0);
        list.delete(0);
        list.delete(0);
        list.reverse();
        System.out.println(list);
        list.length();
        list.append("produce");
        list.append("spoiled milk.");
        list.prepend("Pampered cows");
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
  //----------------------------------------------------------------------
    //===========> HERE STARTS DLinkedStringList <==========================
    //----------------------------------------------------------------------
  /*This will be the class that manages internally DoublyLinkedStringList to
     regulate problematic cases that rise up with having an empty list. Again, 
     this list contains all the methods and constructor of the managed class except for the
     private constructor.*/

    public static class DLinkedStringList implements DoublyLinkedList {

        //New addition to the list is "head", which will work as a node for every method.

        private DoublyLinkedStringList head;
        private DoublyLinkedStringList next;
        private DoublyLinkedStringList prev;
        private final String value;

        public DoublyLinkedStringList getNext() {
            return next;
        }

        public DoublyLinkedStringList getPrev() {
            return prev;
        }

        public String getValue() {
            return value;
        }

        public void setNext(DoublyLinkedStringList N) {
            next = N;
        }

        public void setPrev(DoublyLinkedStringList P) {
            prev = P;
        }

        //Constructor

        public DLinkedStringList(String input) {
            value = input;
            head = new DoublyLinkedStringList(input);
            next = null;
            prev = null;
        }

        @Override
        public DoublyLinkedStringList prepend(String info) {
            if (head == null) {
                //New addition: makes new node.
                head = new DoublyLinkedStringList(info);
                return head;
            } else {
                head = head.prepend(info);
                return head;
            }
        }

        @Override
        public void append(String info) {

            if (head == null) {
                //New addition: makes new node.
                head = new DoublyLinkedStringList(info);
            } else {
                head.append(info);
            }
        }

        @Override
        public DoublyLinkedStringList delete(int index) {
            if (head == null) {
                return head;
            } else {
                head = head.delete(index);
                return head;
            }
        }

        @Override
        public int length() {
            int total = 0;
            if (head == null) {
                //New addition: if null, length will be 0.
                System.out.println("0");
                return total;
            } else {
                total = head.length();
                return total;
            }
        }

        @Override
        public String toString() {

            if (head == null) {
                //New addition: Informs that the list is empty, if null.   
                return "List is empty";
            } else {
                return head.toString();
            }
        }

        @Override
        public DoublyLinkedStringList reverse() {
            if (head == null) {
                //New addition it will not execute the reverse and will only inform that there is no list to reverse.   
                System.out.println("There is no list to reverse");
                return head;
            } else {
                head = head.reverse();
                return head;
            }
        }
    }
}
