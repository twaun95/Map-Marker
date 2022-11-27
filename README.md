# Map-Marker

- map(naver or kakao api)
- bottomFragmentPopup
- 리스트 저장 (dataStore or Room)
- Coroutine Flow, LiveData(binding)
- MVVM
- Clean Architecture
- data binding
- value animator
- Chucker
- 출시
- KTS
- Timber
- LeakCanary
- admob


## 카카오맵 참고

- 현재 위치를 중심으로 맵(제일 처음 켰을 때)
- 현재 위치로 이동
- 로드뷰
- 검색 모드 -> 리스트 선택 하면 마커 표시 -> 마커 클릭하면 바텀네비게이션 보이기
- 현재 위치 중심으로 카테고리별 선택(음식, 카페, 편의점, 약국, 지하철역, 은행) -> 상위 10개? 마커로 표시 -> 마커 클릭하면 바텀네비게이션 보이기
- 바텀네비게이션 올라오면 즐겨 찾기 등록 가능
- 즐겨찾기 리스트

## 앱 이름 고민
음..



## Kakao Map API 사용방법

https://apis.map.kakao.com/android/guide/

### 네이티브 앱 키 발급 및 키 해시 등록하기

키 해시 등록을 위해서는 아래 과정이 필요합니다.
1. 카카오 개발자사이트 (https://developers.kakao.com) 접속
2. 개발자 등록 및 앱 생성
3. Android 플랫폼 추가: 앱 선택 – [플랫폼] – [Android 플랫폼 등록] – 패키지명(필수) 등록
4. 키 해시 등록: [Android 플랫폼 등록] – 키 해시를 등록합니다.
5. 페이지 상단의 [네이티브 앱 키], 등록한 [패키지명], [키 해시]를 사용합니다.

까지 완료.

이후에 라이브러리 파일 추가
