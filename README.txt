============================================================================================================================================================================================
              STOCK EXCHANGE - AUCTION SERVER software
              by E/14/009 & E/14/238
              completed date: 4-10-2017
============================================================================================================================================================================================


This Programme contains 12 .java Files , 1 stocks.csv file and 1 README.txt file.
Main method is in AuctionServer.java file.

So to compile the programme following is the command
javac AuctionServer.java

To run the Auction Server enter following command in terminal
java AuctionServer

Then The GUI for AuctionServer will open up. And same time server will start up.

Server screen
=============
All changes to FB, VRTU, MSFT, GOOGL, YHOO, XLNX, TSLA and TX are displayed real time in display.
Also all the offers are updated in the server display real time.
To change price of an item simply press "Change Price of an Item" button.
To View complete list of items and there prices with security names, press "List Stock items" button.

client
======

clients must connect the auction server using nc client in linux terminal.
Issuing the following command let you connect the server.
nc <host name> 2000

<host name> must be the host name where server is situated.
If remotely situated <host name> is the ip address.
If same PC <host name> is localhost.

After connecting you will prompt to a welcome screen. After that clients must enter there name and hit enter. Then Server ask for the symbol of stock item you want to bid.
clients must enter the symbol and press enter. Then the current price is displayed. Now clients must enter there price and hit enter. Then enter 'confirm' and hit enter to confirm bidding.

If waiting.... is displayed and system is paused. It means that another bidding for the same item going on at that time. After that bidding is over this client can continue.
At any time by entering 'quit' and hitting enter you can exit the Aution Server.

Thank You.
===========================================================================================================================================================================================