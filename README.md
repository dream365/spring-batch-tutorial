Spring Batch Tutorial
=====
> Spring framework에서 batch processing를 사용하고 공부하기 위해 만든 project입니다.

## Batch Components

- Job
    - 배치 처리 과정을 하나의 단위로 표현한 객체
    - 하나의 Job 안에는 여러 Step이 포함
    - JobBuilderFactory의 get()은 새로운 이름을 갖는 job을 새로 생성해서 반환
- Step
    - 실질적인 배치 처리를 정의하고 제어하는데 필요한 모든 정보가 있는 도메인 객체
    - Job 1개당 1개 이상의 Step이 필요 
    - Step 안에는 Tasklet or Reader & Processor & Writer 묶음이 존재 (하나를 선택)
- Tasklet
    - Step 안의 수행될 기능 명시
    - Step 안에서 단일로 수행될 커스텀한 기능(자유로히 해당 STEP에서 하고 싶은 내용을 구현)

## Batch With Mysql

- Mysql을 Spring Batch와 함께 사용하기 위해 Meta Data가 있는 spring-batch db를 만들어줘야 됨 
    - Spring batch core의 schema-mysql.sql 참조 
 
- Meta Data Table
    - BATCH_JOB_INSTANCE
        - Job Param에 따라 Record 생성
        - Job Param이 같다면 생성이 안되며 Spring에서는 JobInstanceAlreadyCompleteException이 발생
