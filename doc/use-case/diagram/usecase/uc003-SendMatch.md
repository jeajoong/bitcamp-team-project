# UC002 - 보낸매치(SendMatch)

보낸 매치를 관리하는 유스케이스이다

## 주 액터(Primary Actor)

팀장

## 보조 액터(Secondary Actor)

팀원

## 사전 조건(Preconditions)

- 보낸 매치 페이지로 들어간다.

## 종료 조건(Postconditions)

- 보낸 매치 목록을 조회하였다.
- 보낸 매치 목록을 수정하였다.

### 기본 흐름(Basic Flows)

- 1. 액터 및 보조액터는 상대팀 로고를 클릭한다. 
- 2. 시스템은 상대팀 정보를 출력한다.
- 3. 액터는 보낸 매치 신청 목록에서 취소를 클릭한다.
- 4. 시스템은 보낸 매치 신청 목록에서 취소된 화면을 출력한다.

### 대안 흐름(Alternative Flows)


### 예외 흐름(Exception Flows)

 -3.1 보조액터가 보낸 매치 신청 목록에서 취소를 클릭했다면,
           -  시스템은 경고창을 출력한다.
    
        
## UI 프로토타입

### 보낸 매치 
![보낸매치](./images/sendmatch.jpg)
    
###보낸 매치 경고창   
![보낸매치경고창](./images/sendmatchwarning.jpg)
 

    
    
    