package pack;
/***********************************************************************/
/*File       : example1.java                                           */
/*Author     : Nikolaos A. Massios                                     */
/*Date       : August 6 1996                                           */
/*Description: This is a small example of how the libraries in Javasim */
/*             are to to be used. This example does not use any        */
/*             graphical features. It is supposed to simulate the way  */
/*             customers in a small town are arriving in a store.      */
/*             at the end of the day the should be a queue of customers*/
/*             waiting to be served. Because the arrival rate is       */
/*             greater than the service rate.                          */
/***********************************************************************/

import java.lang.Math;



class example{
   public static void main(String args[]){
        Entity item[];           /*An array containing all entities*/
        Entity retrieved;        /*The entity just retrieved*/
        Entity nextCustomer;     /*The next customer to arrive*/
        timeTable table;         /*The timetable containing Entities and
                                   the time that they are to be 
	                           triggered*/

        int eventRetrieved;  /*The event just retrieved*/



        /*3 Queues. One that contains the people in the store awaiting
          service one containing the people in the city outside the
          store, and a special one that at the end of the run contains
          the start and end of the day*/
        Queue storeq,worldq,specialq;


        int i;
        int nextTime;       /* the time of the next event*/
        int depart;         /* Whether a customer will depart or not*/
        int clock = 0;      /* The simulation clock*/
        int daylen = 86400; /* Length of day: 60 sec * 60 min * 24
                               hours*/
        int citySize = 400; /* The total number of customers*/


/* Create the Queues.*/
        storeq = new Queue();
        worldq = new Queue();
        specialq = new Queue();

/* Create the Entities.*/
        item = new Entity[citySize+2];
        for(i = 0; i < citySize; i++)
          item[i] = new Entity(i, new String("" + i));
        item[citySize] = new Entity(citySize, new String("Start"));
        item[citySize+1] = new Entity(citySize+1, new String("End"));

/* put the entities in the world queue*/
        for(i=0; i< citySize; i++){
          worldq.addTo(item[i]);
        }

/* Put the two first entities in the store queue(Start and End of day)*/
        storeq.addTo(item[citySize]); 
        storeq.addTo(item[citySize+1]); 

/* set the event for those two entities to the start and end of the day*/
        item[citySize].event = 9;         /*Start of day*/
        item[citySize+1].event = 10;      /*End of day*/
	
/* Then set the timer for those entities*/
        table = new timeTable();
        table.setim(item[citySize], clock);
        table.setim(item[citySize+1], daylen);


/* The next 3 lines start the scan cycle. We scan the next Entity/Event
   and deal with it using a case statement. Then when the end of the 
   day is reached (event number 10) we stop*/
        retrieved = table.scan();
        clock = retrieved.time;
/*find out the event associated to the Entity*/
        eventRetrieved = retrieved.event;
        while(eventRetrieved != 10){
          switch(eventRetrieved){
             case 1:  /*Arrival of a customer*/
                System.out.println(retrieved.value + 
                                   " arrived at time :" + clock);

             /* add him to the queue*/
                storeq.addTo(retrieved);

             /* decide how much time it takes serving this customer*/
             /* maximum 60 seconds, 25% probability he never leaves*/
                depart = (int)  Math.floor(Math.random() * 3);
                if(depart != 0){ /* some will never depart*/
                  nextTime = clock +
                             (int) Math.floor(Math.random() * 60);
                  table.setim(retrieved,nextTime);
                  retrieved.event = 2; /*departure*/
                }

             /* also find out when the next customer is comming*/
                nextTime = clock +
                           (int) Math.floor(Math.random() * 1000);
                nextCustomer = (Entity) worldq.randomSelect();
                nextCustomer.event = 1; /*arrival*/
                table.setim(nextCustomer, nextTime);
                break;
             case 2:  /*Departure of a customer*/
                System.out.println(retrieved.value + " left at time :" +
                                   clock);
                storeq.erase(retrieved);
                worldq.addTo(retrieved);
                break;
             case 9:  /*Start of the day*/
                storeq.erase(retrieved);
                specialq.addTo(retrieved);
                nextTime = clock +
                           (int) Math.floor(Math.random() * 1000);
                nextCustomer = (Entity) worldq.randomSelect();
                table.setim(nextCustomer, nextTime);
                nextCustomer.event = 1; /*arrival*/
                break;
          }

          /*get the next entity, find the next clock, find what the event
            associated to that entity is*/
          retrieved = table.scan();
        //  System.out.println("WAA:" +retrieved);
          clock = retrieved.time;
          eventRetrieved = retrieved.event;	
        }
        storeq.erase(retrieved);         /*remove End from the storeq*/
        specialq.addTo(retrieved);       /*add it to the special q*/

  
        /*Since the store closes all the customers planning some
          customers planning to come did not make it in the storeq.
          Big fat security guy sitting in the door is not letting
          them in*/
        while(table.size() != 0){        
          retrieved = table.scan();
          worldq.addTo(retrieved);
        }


/* These print statements are here to show to user why we stopped.
   We also  display the sizes of the various queues to show what was the
   situation at the end of the day. */
                                /*Say why you stopped*/
        System.out.println("stoped at :" + retrieved.value + 
	                   " time: " + clock+ " event: " +
                           eventRetrieved);
        System.out.println("No items " + (citySize + 2));
        System.out.println("No items found " + (worldq.size() +
	                    storeq.size() + specialq.size()));
                               /*Print the sizes of all the queues*/
        System.out.println("Worldq size " + worldq.size());
        System.out.println("Storeq size " + storeq.size());
        System.out.println("Specialq size " + specialq.size());
        System.out.println("" +System.currentTimeMillis());
   }
}