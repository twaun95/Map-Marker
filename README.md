# Map-Marker

- Clean Architecture + MultiModule + MVVM
- Coroutine, Flow
- JetPack
  - Data binding
  - DataStore
  - Pagin3g
- Retrofit, Glide, Timber, LeakCanary
- RecyclerView(ListAdapter), WebView(url 클릭시 이동)
- kakao api
  - kakao Map
  - 장소 검색
    - 키워드
    - 카테고리
  - 주소 검색
- FireBase
- Kotest


## 카카오맵 참고

- 현재 위치로 이동
- 검색 모드 -> 리스트 선택 하면 마커 표시 -> 마커 클릭하면 바텀네비게이션 보이기
- 현재 위치 중심으로 카테고리별 선택(음식, 카페, 편의점, 약국, 지하철역, 은행) -> 상위 10개? 마커로 표시 -> 마커 클릭하면 바텀네비게이션 보이기
- 바텀네비게이션 올라오면 즐겨 찾기 등록 가능
- 즐겨찾기 리스트

## 앱 이름 고민
음..

### 메모

-type 지정
public MapView.MapType getMapType()
public void setMapType(MapView.MapType mapType)
지도 종류(기본 지도, 위성 지도, 하이브리드 지도)를 지정


- 트래킹모드
현위치 트래킹/나침반 모드를 활성화 시키면 현위치 정보가 설정된 MapView.CurrentLocationEventListener 객체에 전달된다.

현재 모드
public MapView.CurrentLocationTrackingMode getCurrentLocationTrackingMode()


- 현위치 아이콘, 현위치 트래킹 아이콘 설정
- 말풍선
- POI?
- 키워드로 검색, 카테고리로 검색, 주소 검색
