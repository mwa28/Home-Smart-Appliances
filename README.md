# Home Smart Appliances
  The project is about developing a smart home-appliance application that will interface the appliances of a house with servers in supermarkets and appliance dealers.
  
  The appliances are smart in that they monitor their supply using sensors (assume that the technology is there) and they electronically place orders for those items that are low in quantity to the supermarket, with which they are setup. Moreover, if a smart appliance senses that something is about to go wrong with its system, it places an electronic order for a maintenance/repair job. In this respect, the appliances will be the clients while the network applications of the supermarket and the dealer are the servers.
  
  Every appliance must keep in its configuration file “ordering thresholds” for all the items it stocks or it consumes, in addition of course to the quantity or volume of those items. When an item’s quantity drops below the threshold, then the item is added to the “to order list”. The appliance places an electronic order if the number of items in this list exceeds the “ordering threshold”, or if the quantity of any items in the appliance reaches a zero.
  
 For this project, you need at leas two physical computers. One (say Machine M1) that runs the user’s process and the appliances, while the other one (say Machine M2) runs the supermarket’s server and the dealer’s server processes. Every process on M1 is a process by itself, where the user is the only process that has a graphical user interface, while each appliance runs two threads each of which executes in an infinite loop (thread one is for keeping track of the items and thread 2 is for simulating malfunctions). Similarly, every server on M2 is a separate process that starts a thread for each client that connects to it.
 
  The user process interacts with the appliance processes via configuration files, where the user is presented with a list of items in categories for each appliance. He or she can then consume items asynchronously (i.e., in increments and at different times whenever desired). The appliance must somehow be informed about the consumption and react accordingly (i.e., to compare the quantities versus the thresholds). 
  
  The user process interacts with the supermarket and dealer processes via sockets to request account summaries (per week, month, and year) and owed bills. Simulate the capability for the user to pay the owed amount by credit card.
  
  The appliance processes interact with the supermarket and dealer processes via sockets to submit orders for merchandise items and place repair/maintenance orders. To make this task easy, you can assign to each merchandize item a unique code that is understood by both the appliance and the servers. However, when the bill is sent to the user, the description of each item must be included on the bill in addition to the code. For the project, the total number of possible items should be at least 25, which the group is free to configure. The supermarket server must keep and update its inventory list.
  
  As part of the socket communications between the user process and the servers and between the appliances and the servers, you will need to design commands to be used for the communication protocols. For example, one might use the following command that the refrigerator uses to order from the supermarket’s server 5 liters of apple juice, 3 bottles of diet Pepsi, 4 dozens of eggs, and 2 kilos of cucumbers: 
  
  MEM_ORDER App 5 Pepsi 3 Eggs 4 Cucum 2.
  
  As the example above shows, every item is described by three tuples: Item keyword, quantity, and unit of measure (notice how the data of the items are separated by semicolons) and how the end of the order is terminated with a dot. Finally, the command “MEM_ORDER” tells the server that this text represents a merchandize order.
  
  Regarding the choice of transport layer protocol, all messages (commands) and data sent between the servers and the clients are via TCP.
