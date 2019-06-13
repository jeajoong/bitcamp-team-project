# UC001 - 회원 가입(Sign Up)
비회원이 시스템을 이용하기 위해 회원을 가입하는 유스케이스

## 주 액터(Primary Actor)
비회원

## 보조 액터(Secondary Actor)

## 사전 조건(Preconditions)
- 회원에 가입하지 않은 상태이다.
- 회원 가입시에 입력하는 이메일이 시스템에 등록되어 있지 않다.

## 종료 조건(Postconditions)
- 회원 정보가 시스템에 등록되었다.

## 시나리오(Flow of Evnets)

### 기본 흐름(Basic Flows)

- 1. 액터(비회원)가 회원 가입 버튼을 클릭할 때 이 유스케이스를 시작한다.
- 2. 시스템은 이용 약관 폼을 출력한다.
- 3. 액터(비회원)는 약관 동의를 체크하고 다음 버튼을 클릭한다.
- 4. 시스템은 회원 가입 폼을 출력한다.
- 5. 액터(비회원)는 가입 폼에 맞게 정보를 입력한 후 가입 버튼을 클릭한다.
- 6. 시스템은 회원 정보를 저장한 후 액터에게 '메인' 이나 '로그인' 버튼 중 하나를 선택할 수 있도록 한다.

### 대안 흐름(Alternative Flows)

- 5.1 액터가 페이스북 계정으로 회원가입 버튼을 클릭하면,
    - 시스템은 '페이스북 계정으로 회원 가입하기' 유스케이스로 간다.
- 5.2 액터가 카카오 계정으로 회원가입 버튼을 클릭하면,
    - 시스템은 '카카오 계정으로 회원 가입하기' 유스케이스로 간다.
- 5.3 액터가 구글 계정으로 회원가입 버튼을 클릭하면,
    - 시스템은 '구글 계정으로 회원 가입하기' 유스케이스로 간다.

### 예외 흐름(Exception Flows)

- 6.1 가입 폼에서 필수 입력란이 한 개라도 값이 비어 있으면,
    - 시스템은 필수 입력 항목이 비어 있음을 알리는 내용을 출력한다.
- 6.2 이메일이 이미 등록되어 있다면,
    - 시스템은 중복된 이메일임을 알리는 내용을 출력한다.
- 6.3 처음 설정한 암호와 재확인 암호가 일치하지 않으면,
    - 시스템은 입력된 암호가 일치하지 않음을 알린다.
