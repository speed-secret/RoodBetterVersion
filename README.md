# BetterVersion
------------------------------------
# RootCodeChallenge
Conquer The Interesting Challenge!

In this file, there are two parts:
For the first part, I explain the thinking process of using the OOD mindset from High level to details,
For the second part, I went through a work sample case to prove the correctness of my logic and my solution(in each step) to this question.

************************************
Part One
************************************

Clarify:
Firstly, the file can be fit in the memory;
Assume there should be no duplication for drivers’ name;
Assume all the drivers name information should present before each trip status;

High level:
For the scenario that the example showed above, I was thinking of using a tokenizer to read each list of 
input string then to subtract them into each piece of single string and drill it down -> calculate each driver’s status 
including the total distance and average speed

Details:
Using two extra classes excluding the solution class;
the first class is Driver which contained 4 fields: 
name, 
sumOfDistance,  
averageSpeed, 
preTime.

the second class is Trip which also include fields as 
int[] time, 
distance

as well as two methods:
1. calculate each time that spend in each trip;
2. calculate the curAverageSpeed;

For the input:
There are mainly two cases in terms of the first element of each list of string:

The first case is that the first string in the list is “Driver” which indicates the name of participant driver
The second case is the that the first string in the list is”Trip” which reflect the status of each trip including distance, cost of time, and speed(we can figure it out later);

The purpose of this project is to focus on calculating the average speed of each Drivers.
Hence, I use a hashMap to store each driver’s name as the keys and each Driver(which take 4 fields)class as values;
for the Driver’s name stuff, we just initialize the hashMap,
then when we process each trip, we shall calculate each trip’s speed and distance and update the corresponding Driver class in the hashMap;

After we processed all the input, we shall scan every entry in the current hashMap and sort all the driver’s status (in this example, sort the Drivers’ distance by High to low)

************************************
Part Two
************************************
Note: here is the example by using my logic:
input:
{“Driver”, “Dan”}
{“Driver”, “ Lauren”}
{“Driver”, “ Kumi”}
{“Trip”, “ Dan”, “ 07:15”, “ 07:45”, “17.3”}
{“Trip”, “ Dan”, “ 06:12”, “ 06:32”, “21.8”}
{“Trip”, “ Lauren”, “ 12:01”, “13:16”, “42.0”}

Step1: 
I create Driver class with 4 fields:

name, 
sumOfDistance,  
averageSpeed, 
preTime;

then to create Trip class with 2 fields and 2 methods

Fields:
int[] time(use to calculate the start/end hour and start/end minute)
distance(use to record the distance in a single trip)

Methods:
getTimeCost();
getAverageSpeed();

Step2:
create a hashMap to store each Driver’s name(string) a key and the Driver class as value
Map<String, Driver> driverMap = new HashMap<>();

Step3:
Process all list of Strings(input)
driveMap: <Dan, (Dan) Driver>
          <Lauren, (Lauren)Driver>
	  <Kumi, (Kumi)Driver>

case1: Driver name only -> initialize driverMap with all new drivers name;
case2: Process Trip status and update corresponding Driver’s field in the map
{“Trip”, “ Dan”, “ 07:15”, “ 07:45”, “17.3”}
update: driverMap<Dan, (Dan) Driver>
		      name: Dan
		      sumOfDistance: 17.3 miles
		      aveSpeed: 34.6mph
		      preTime: 0.5h
                      
{“Trip”, “ Dan”, “ 06:12”, “ 06:32”, “21.8”}
update: driverMap<Dan, (Dan)Driver>
                      name: Dan
                      sumOfDistance:39.1miles	
                      aveSpeed: 46.92mph
                      preTime: 0.83h

{“Trip”, “ Lauren”, “ 12:01”, “13:16”, “42.0”}
update: driverMap<Lauren, (Lauren)Driver>
                       name: Lauren
                       sumOfDistance:42.0 miles	
                       aveSpeed: 33.6mph
                       preTime: 1.25h

Step4:
Sort each entry in the driverMap and round the totalDis and aveSpeed and sort the totalDis by Descending order
driverMap<Dan, (Dan)Driver>
                name: Dan
                sumOfDistance:39.1miles	
                aveSpeed: 46.92mph
                preTime: 0.83h

driverMap<Lauren, (Lauren)Driver>
                   name: Lauren
                   sumOfDistance:42.0 miles	
                   aveSpeed: 33.6mph
                   preTime: 1.25h

driverMap<Kumi, (Kumi)Driver>
                 name: Kumi
                 sumOfDistance:0.0miles	
                 aveSpeed: 0mph
                 preTime: 0h

Step5:
OutPut (Print out the result)

Lauren: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Kumi: 0 miles
