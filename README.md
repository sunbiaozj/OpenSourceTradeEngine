OpenSourceTradeEngine
=====================

This program serves as a basis for an open source order/trade matching application. It will take in a stream of orders (Market, Limit, or Remove) and match them up.

The list node folder in src holds the java classes for the objects which make up the actual working trade engine. OrderListManager.java is the class which has the main method. All it does is create the main loop
