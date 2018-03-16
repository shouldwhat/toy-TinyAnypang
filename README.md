# toy-tinyAnypang

* **프로젝트 소개**
```
	*. 5x5 스테이지의 작은 애니팡 게임.
	
	*. 가로, 세로 3개 이상 동일한 값인 위치 추출 프로세스 구현.
	
	*. 동일한 값인 위치 제거 후 다음 스테이지 생성 프로세스 구현.
```

---

* **개발 환경**
```
	*. JDK : 1.8.0_144
```

---

* **클래스 구조**
![](/images/class.png)

---

* **주요 클래스 설명**
```
	*. AnyPangGame : 게임의 프로세스를 제어하는 클래스.
	
	*. StageManager : 스테이지 생성, 재구성 등 스테이지 object에 대한 작업 수행
	
	*. Detector : 스테이지 내 제거해야할 위치를 추출하는 작업을 수행하는 추상화 클래스.
```

---

* **시퀀스**

	*. **게임 시작(=run)**
	![](/images/sequence_run.png)
	
	
	
	*. **제거 대상 추출(=detectLinkedPosition)**
	![](/images/sequence_detectLinkedPosition.png)
	
	*. **다음 스테이지 구성(=refreshAndNextStage)**
	![](/images/sequence_refreshAndNextStage.png)

---

* **추가 개발 예정**
```
	*. 사용자 입력에 의한 스테이지 위치 조정 API.
	
	*. 사용자 입력에 의한 다음 스테이지 이동 여부 체크 API.
	
	*. 대각선 방향으로 제거 대상 추출 프로세스 API.
```

---
