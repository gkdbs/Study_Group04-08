import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.kakao.util.maps.helper.Utility;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Kakaomaps extends AppCompatActivity {

    //카카오지도는 실제 디바이스에서만 동작함. 에뮬레이터에는 동작하지 않음-에러 ///

    // Kakao 지도 API 라이브러리는 Gradle 방식이 아니어서
    // build.gradle 을 이용하여 자동 다운로드 되는 방식을 사용할 수 없음!
    // 지도 라이브러리 클래스들을 압축문서(.jar)로 만들어서 제공함.
    // 프로젝트의 적절한 위치에 복사하고..복사한 이 라이브러리를 프로젝트에서 인식하도록 연결!!

    //카카오 지도뷰 객체 참조변수
    MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Kakaomap);

        //키 해시값 얻어오는 기능메소드 호출 (Utility클래스 - kakao라이브러리에 있는)
        String keyHash= Utility.getKeyHash(this);
        //로그값을 키해시값 출력하기 (저 아래 Logcat이라는 탭에 출력해 주는 기능)
        Log.i("KEY", keyHash);


        //맴뷰객체 생성
        mapView= new MapView(this);

        //xml에 있는 멤뷰컨테이너용 뷰(RelativeLayout)에 맵뷰 추가
        RelativeLayout mapContainer= findViewById(R.id.map_container);
        mapContainer.addView(mapView);

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(7, true);

        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);

        // 줌 인
        mapView.zoomIn(true);

        // 줌 아웃
        mapView.zoomOut(true);


        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");
        marker.setTag(0);

        MapPoint point=MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633);
        marker.setMapPoint(point);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);



    }
}