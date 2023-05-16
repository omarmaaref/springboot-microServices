# springboot-microServices

## Group :
Med Khalil Ben Abdallah
Youssef Smeoui
Omar Maaref

## UML:
![Screenshot from 2023-05-06 00-59-54](https://user-images.githubusercontent.com/64233476/236586986-02cb6d9c-970f-493a-b4e6-a55d6b5edb3a.jpg)

i'm providing 2 microservices :

orchestrator : on port 8081 (Springboot)
( to simplefy running the program :loan request is done via a Get request )
controller:
![image](https://user-images.githubusercontent.com/64233476/236585984-28052033-e8a9-47e6-a699-c9d01187635d.png)
service:
used the Iterator Pattern to check loan validation steps in order :
![image](https://user-images.githubusercontent.com/64233476/236586025-94e29c9f-c09a-4c9c-8b86-01f778c2e4a3.png)
![image](https://user-images.githubusercontent.com/64233476/236586105-46dc0138-befb-4098-b1de-2e8cf70a5d32.png)
communication with form manager server is done by HTTP calls:
![image](https://user-images.githubusercontent.com/64233476/236586252-811421e4-3d0f-464f-89b4-849f5c1dfff6.png)


formManager : on port 8082 (Springboot)
![image](https://user-images.githubusercontent.com/64233476/236585861-e5ced3b0-8891-40a5-b149-eadfd00082ab.png)


## live demo:
https://drive.google.com/file/d/1hWi5n5PTA5A2Kwcc3HJSYqNapp9Ux9g6/view?usp=sharing

Note: as you can see the communication using traditional REST protocal is
  1. complicated ( code wise) .
  2. errors can't be investigated & fixed easily 
  3. security threat due to communication format being general ( no constraints on requests made)
  
## Solution:
  use a protocal like "gRPC" which integrates natively with our orchestrator service ( making our app look & feel monolith)
  
