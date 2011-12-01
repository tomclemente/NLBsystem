package pack;

import java.awt.*;
import java.lang.Math;



class example3{
   public static void main(String args[]){
        Entity item[];          /*An array containing all entities*/
        Entity retrieved;       /*The entity just retrieved*/
        Entity nextCustomer;    /*The next customer to arrive*/
        GraphTimeTable table;   /*The timetable containing Entities 
                                  and the time that they are to be
                                  triggered*/

        int eventRetrieved;     /*The event just retrieved*/


        /*3 Queues. One that contains the people in the store awaiting
          service one containing the people in the city outside the
          store, and a special one that at the end of the run contains
          the start and end of the day*/
        GraphQueue storeq,worldq,specialq;

        int i;
        int nextTime;       /* the time of the next event*/
        int depart;         /* Whether a customer will depart or not*/
        int clock = 0;      /* The simulation clock*/
        int daylen = 86400; /* Length of Day: 60 sec * 60 min * 24 
                               hours*/
        int citySize = 400; /* The total number of customers*/

/*Graphics stuff*/
        Frame myFrame;      /* The frame on which everything is added*/
        Panel Queues;       /* The Panel that contains the queues*/
        Panel clockPanel;   /* The Panel that contains the clock*/
        TextArea myText;    /* The text area for printing*/
        /*Create a girdbag layout and its constraints*/
        GridBagLayout gridbag = new GridBagLayout();   
        GridBagConstraints c = new GridBagConstraints();
        
        /*Create the new frame*/
        myFrame = new Frame("Visual Queues");
        myFrame.setLayout(gridbag);

        /*Create the new Queues panel*/
        Queues = new Panel();
        Queues.setLayout(new GridLayout(0,1));

/* Create the Queues. One queue is the customer queue the other is the
   world queue*/
        storeq = new GraphQueue("Store q size: ",Queues,200);
        worldq = new GraphQueue("World q size: ",Queues,400);
        specialq = new GraphQueue("Special q size ",Queues,2);

        /*Set the contrains and add Queues to the frame*/
        c.gridx = 0;
        c.weighty = 0.2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        gridbag.setConstraints(Queues,c);
        myFrame.add(Queues);

        /*Create the Clock set the constraints and add it to the 
          frame*/
        clockPanel = new Panel();
        table = new GraphTimeTable(clockPanel,"Clock time(in sec): ");
        c.gridy = 1;
        gridbag.setConstraints(clockPanel,c);
        myFrame.add(clockPanel);


        /*Create the text area set the constraints add add it to
          the frame*/
        myText = new TextArea("",10,40);
        myText.setEditable(false);
        c.gridy = 2;
        c.weighty = 1.0;
        gridbag.setConstraints(myText,c);
        myFrame.add(myText);

        /*Change the background color*/
        myFrame.setBackground(new Color(26,150,126));

        /*Display the frames*/
        myFrame.pack();
        myFrame.show();

/*The rest of the code is the same as test.java*/

/* Create the Entities. */
        item = new Entity[citySize+2];
        for(i = 0; i < citySize; i++)
          item[i] = new Entity(i, new String("" + i));
        item[citySize] = new Entity(citySize, new String("Start"));
        item[citySize+1] = new Entity(citySize+1, new String("End"));

/* put the entities in the world queue*/
        for(i=0; i< citySize; i++){
          worldq.addTo(item[i]);
        }

/* Put the two first entities in the store queue*/
        storeq.addTo(item[citySize]); 
        storeq.addTo(item[citySize+1]); 

/* crearte two events associated to the start and end entities*/
        item[citySize].event = 9;
        item[citySize+1].event = 10;
        
/* Then set the timer for those entities*/
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
                myText.appendText(retrieved.value + 
                                  " arrived at time :" + clock + "\n");

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
                myText.appendText(retrieved.value + " left at time :" +
                                   clock + "\n");
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
          clock = retrieved.time;
          eventRetrieved  = retrieved.event;    
        }
        storeq.erase(retrieved);     /*remove End from the storeq*/
        specialq.addTo(retrieved);   /*add it to the special q*/

  
        /*Since the store closes all the customers planning some
          customers planning to come did not make it in the storeq.
          Big fat security guy sitting in the door is not letting 
          them in*/
        while(table.size() != 0){        
          retrieved = table.scan();
          worldq.addTo(retrieved);
        }

                                         /*Say why you stopped*/
        myText.appendText("stoped at :" + retrieved.value + " time: " +
                           clock+ " event: " + eventRetrieved + "\n");
   }
}