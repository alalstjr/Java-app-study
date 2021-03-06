목차
- [1. Kotlin 언어](#Kotlin-언어)
- [2. class](#class)
- [3. constructors](#constructors)
- [4. 상속](#상속)

# Kotlin 언어

- Android 공식 개발 언어로 2017년 추가됨 (공개는 2011년)
    - 2014년 IOS에서 기존 Objective-C에 Swift 언어 추가
- 현대 언어 특징 갖춤
    - 간결한 코드
    - 높은 표현력
    - 생산성 향상
- Null Pointer Exception (NPE) 방지
    - 익수하지 않은 ?를 사용한 문법
- 이론 보다는 실리
    - Getter/Setter를 사용하지 않고 default visibility 가 public
- for loop 문법이 다름
- 이름 짓는 규칙
- 여러 단어일때 Camel case
- class 및 object(명사): 첫글짜 대문자
    - derived : base (파생 클래스 한 칸 띄고 콜론, 띄우고 기반 클래스)
- var, val(명사) : 첫 글자 소문자
    - 변수명: 타입 (변수명 바로 뒤에 콜론, 한 칸 띄고 타입)
- fun (동사) : 첫 글자 소문자
    - Factory fun: 첫 글자 대문자
- const: 모두 대문자 및 _ (under bar) 사용
- 블록 시작 중괄호는 한 줄 내리지 말고 오른쪽에 위치

# class

- Java 나 C++ 와 같은 class 키워드 사용
    - Java의 extends 대신 C++ 의 간결한 : (콜론) 사용
        - 콜론 앞 뒤에 space
    - 상속받을 클래스 및 구현할 인터페이스를 ,(콤마)로 구분하여 :(콜론) 뒤에 나열
- 예제
~~~
class MainActivity : AppCompatActivirt() {}
~~~
- MainActivity 클래스는 AppCompatActivity 클래스로부터 상속 받음
- AppCompatActivity 클래스 정의 앞에는 open 이 붙어 있을 것임
    - 실제로는 Java 클래스임
- MainActivity 클래스의 생성자는 AppCompatActivity 클래스의 파라미터 없는 생성자 호출

# constructors

- 생성자
     - 클래스 이름 뒤에 골호를 붙이지 않고 constructor 키워드 사용
     - 예제
~~~
class MyView : View {
    constructor(context: Context)
        : this(context, attrs: null)
    constructor(context: Context, attrs: AttributeSet?)
        : this(context, attrs, defStyleAttr: 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
        : super(context, attrs, defStyleAttr)
}
~~~
- 총 3개의 생성자
- 가장 마지막의 3개 파라미터 받는 것이 super 클래스(View)의 생성자 호출
- Primary 생성자
    - class header에 클래스 이름 뒤에 정의
- init 블록
    - 인스턴스 생성할 때 실행됨

# 상속

- 최상위 클래스 Any
    - 명시하지 않아도 암시적으로 상속됨
    - equals(), toString(), hashCode()과 확장 함수
- Kotlin 언어의 철학은 명시적으로 만드는 것임
    - 재정의 가능 멤버에 open 명시적으로 붙여줘야 함 (클래스를 설계를 할때 상속(sub) 클래스에서 가져다가 쓸수 있는 멤버일경우)
    - C++ 에서는 virtual로 open 및 override 혼용
- Super class는 open, subclass는 override 사용
    - 한 sub 클래스에서 더 이상 자신의 sub 클래스에서 재정의하지 못하게 하려면 final override 사용
- Overriding 에서도 open, override 사용
     Super class에서 open인 fun만 subclass에서 override 가능

# override

- 재정의 (overrding)
    - super: super (base) class 의미
        - super 를 사용해 (base) class의 해당 함수 호출
~~~
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
    }
}
~~~

- super@outer클래스이름
    - inner class인 경우에 outer class의 이름 지정 가능
- super<클래스 또는 인터페이스 이름>.멤버()
    - super 클래스 및 구현하는 interface의 멤버 이름이 같을 때 구분 가능

# companion object

- static
    - 인스턴스를 생성하지 않아도 클래스를 통해 접근 가능
    - Kotlin에서는 static 없음
- Kotlin에서는 한 클래스에 오직 한 개의 companion object 가질 수 있음
~~~
class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val REQUEST_GUNDAM = 100
        const vall KEY_NAME = "name"
    }
}
~~~
- 다른 클래스에서 MainAcitivity.REQUEST_GUNDAM과 같이 접근 가능

# object

- 새로운 class를 만들지 않고 상속을받아서 재정의할 때 사용
    - Java의 anoymous inner class와 유사 (이름을 정하지않고 class의 구현을 바로만들때)
    - 예제, ImageLoader.ImageCache class에 대해 object 생성
        - 별도 class 정의 없이 함수 내에서 인자로 구현
    - Functional Interface는 Lambda식 가능
~~~
mImageLoader = ImageLoader(mQueue,
    object : ImageLoader.ImageCache {
        private val cache = LruCache<String, Bitmap>(maxSize: 20)

        override fun getBitmap(url: String): Bitmap? {
            return cache.get(url)
        }
        override fun putBitmap(url: String, bitmap: Bitmap) {
            cache.pur(url, bitmap)
        }
    }
)
~~~

# data class

- 구글에서 2017년 Kotlin을 처음 소개하면서 자랑스럽게 보여줬던 영상에 나옴
    - "Java에서 이만~큼 긴 소스 코드를 Kotlin에서는 단 한줄만 쓰면 됩니다."
- Default visibilit가 public 
    - C++ 언어의 struct도 default 접근 지정자가 public 
    - `struct 와 class의 유일한 차이점은 (default 접근 지정자가 public 이면 struct) 반대로 private면 class`
- 예제
~~~
data class Gundam(var model: String, var name: String, var image: String)
~~~
- 3 개의 멤버 가짐
- val g = Gundam("RX-78", "건담", "건담.jpg")로 초기화 가능
- 사용할 때는 g.model로 접근 가능

# 변수

- 두 종류
    - val
        - read-only
    - var
        - 할당 가능
~~~
private val paint: Paint = Paint()
private var bmp: Bitmap
~~~
- 타입
    - 변수이름: 타입
    - 값을 초기화하는 경우에 값의 타입이 명확하다면 변수의 타입을 지정하지 않아도 됨
- 타입 뒤에 ?(물음표)가 붙는 경우는 변수가 null을 가리킬 수 있는 `nullable`이라는 의미
    - Null Safety 참조

# lateinit

- constructor()에서 초기화할 때는 안써도 됨
- Nullable일 때는 쓸 수 없음
- isInitialized로 검사 가능
- Nullable이 아닌데 초기화 하지 않을 때 사용
    - 예제) mImageLoader 변수 만들고 onCreate()에서 초기화
~~~
lateinit var mImageLoader: ImageLoader

override fun onCreate(saveInstanceState: Bundle?) {
    super.onCreate(saveInstanceState)
    setContentView(R.layout.activity_main)

    mImageLoader = ImageLoader(mQueue,)
}
~~~

# fun

- 함수 (function)
    - 앞에 fun 붙여야 함
- Parameter
    - 변수 선언과 같이 변수이름: 타입 형식
    - Parameter가 여러 개 일때는 콤마(,)로 분리
- Unit: 반환값 없음
    - Unit인 경우 생략 가능
- 예제
~~~
override fun getItemId(position: Int): Long {
    return position.toLong()
}
~~~
- 반환 값은 Long타입이고 파라미터는 Int 하개를 갖는 getItemId()함수 정의

# 함수 활용

- Default Arguments
    - 함수 호출시에 지정하지 않으면 Default 값 지정
~~~
fun myFunc(value: Int, text: String = "") { }

fun caller() {
    myFunc(value: 100)
}
~~~

- Named Arguments
    - IDE에서 회색으로 보여주는 것과 비슷하게 명시적으로 쓸 수 있음
    - 순서 바뀌어도 됨
~~~
fun myFun(value: Int, text: String) { }
fun caller() {
    myFunc(text="Hello", value=100)
}
~~~

# String Templates

- 유용하게 사용할 수 있는 문법
    - printf()의 %d 생각하면 됨
    - 따로 분리하지 않고 스트링 내에 집어 넣음
- $ 사용, {}도 사용 가능
- 예제
~~~
holder.imImage.setImageUrl(
    url:"${SERVER_URL}/images/${mArray[position].image}", mImageLoader
)
~~~
- url 지정하는 두 번째 줄
- ${SERVER_URL}의 내용이 스트링에 들어가고 바로 뒤이어 images/가 들어가며 그 뒤로 mArray의 position번째에 저장된 image스트링 값이 들어감

# Visibility Modifiers in Classes (접근 지정자)

- private
    - 현재 클래스 내부에서만 보임
    - Outer 클래스에서도 못 봄
- protected
    - 현재 클래스 및 파생 클래스
- internal
    - 같은 모듈(같이 컴파일되는 코틀린 파일들의 집합) 안에서 internal 멤버를 선언한 클래스를 볼 수 있다면 접근 가능
        - 한 클래스 내부에 protected 클래스가 있고 거기에 internal 멤버가 있다면 바깥의 다른 클래스에서는 접근하지 못함
- public (default)
    - public 멤버를 선언한 클래스를 볼 수 있다면 접근 가능

# Null 안전성

- Kotlin에서는 `null pointer exception(NPE) 발생 제거 노력`
- C++, Java 언어에는 없는 특이한 문법
- 변수는 크게 두 가지 종류: non-null 및 nullable
- Non-null
    - null 값을 가질 수 없는 변수
    - null을 가질 수 없으므로 NPE 발생하지 않음
- Nullable
    - null 값을 가질 수 있는 변수
    - null 객체의 함수를 실행하는 경우 null 반환
        - C++, Java의 경우 NPE 발생하고 프로그램 종료

# Nullable

- Reference에 `null 값이 들어갈 수 있다면 nullable이라고 명시`해 줘야함
- 타입 오른쪽에 ?(물음표) 사용
    - 예제, var str: String?
- Elvis 연산자 (?:) (삼항 연산자와 비슷한 느낌)
    - null 일 경우 뒤의 표현식 반환 (또는 throw로 exception 발생)
    - 예제, val len: Int = str?.length ?: -1
        - str 값이 null 이 아니라면 length null 이라면 -1
- !!
    - null이 아님이 확실하거나 또는 null일 경우 NPE 발생시킬 때 사용
    - null 체크를 한 상태면 넣어주면 된다.
- as? (Safe cast연산)
    - 다른 타입으로 cast가 불가능하다면 null 반환
    - 예제, val len: Int? = arg as? Int

# NetworkImageView 사용시

- 개발자 공식 문서의 코드에서 실행시 에러 발생
    - getBitmap()의 return 타입 Bitmap을 Nullable로 변경하면 해결됨
    - Bitmap?
~~~
mImageLoader = ImageLoader(mQueue,
    object : ImageLoader.ImageCache {
        private val cache = LruVache<String, Bitmap>(maxSize: 20)

        override fun getBitmap(url: String): Bitmap? (? 추가하여 nullable 변경) {
            return cache.get(url)
        }

        override fun putBitmap(url: String, bitmap: Bitmap) {
            cache.put(url, bitmap)
        }
    }
)
~~~

# Lambda Expression

- Anonymous functions
    - 이름이 붙지 않고 구현만 있음
- Lambda 식만 함수 인자로 전달한다면 괄호 생략 가능
    - 예제: test({doit()}) 대신 test{ doit() }
- it : lambda 식이 한 개의 parameter만 갖는다면 -> 를 생략 하고 이름은 it 사용
- SAM (Single Abstact Method) interface
    - interface에 method 한 개인 경우(많음)
        - 예제에서는 onCLick()
~~~
button.setOnClickListener {
    it: View!
    val btn : Button = it as Button
    textView.text = btn.text
}
~~~

# Lambda 식에서의 _ (Underbar)

- `사용하지 않는 Parameter`에는 이름을 부여하지 않고 _ 를 붙여서 자리만 차지
~~~
listView.setOnItemClickListener { adapterView, view, i, l -> }

listView.setOnItemClickListener { _, _, i, _ ->
    Toast.makeText(context: this,
        mArray[i].name,
        Toast,LENGTH_SHORT
    ).show()
}
~~~
- 리스트에서 Item Click에 대한 리스너를 Lambda 식으로 구현
- 총 4개의 파라미터가 있지만 구현할 때는 3번째의 몇번째 아이템이 선택되었는지만 필요하여 사용하지 않는 나머지 _ 사용

# when

- 다른 언어의 switch-case 문 생각하면 됨
- 다른 언어에 비해 강력함
- Nullable 변수에서 Int 처리 에제
~~~
when(v?.id) {
    btnGundam.id -> untent.putExtra(KET_NAME, value: "RX-78")
    btnZaku.id   -> intent.putExtra(KEY_NAME, value: "MS-05")
    null         -> return
}
~~~
- 스트링도 처리 예제
~~~
when(intent.getStringExtra("name")) {
    "RX-78" -> imageView2.setImageResource(R.drawable.rx78)
    "MS-05" -> imageView2.setImageResource(R.drawable.ms05)
    else    -> imageView2.setImageResource(R.drawable.ic_launcher_background)
}
~~~

# for

- 다른 언어의 전형적인 `for loop는 허용하지 않음`
    - for(int i = 0; i < 10; i++) 안됨
- 일반적인 경우
    - `for(i in 0..9)`로 써야함
    - downTom step 있음
        - for(i in 9..0) 안됨, for(i in 9 downTo 0) {
- 숫자가 정해지지 않은 경우
    - for(i in 0 unitl mArray.size)
- 컬렉션 처리
    - for(item in mArray)
~~~
for (i in 0 until item.length()) {
    val item: JSONObject = items[i] as JSONObject
    val model: String = item.getString("model")
    val name: String = item.getString("name")
    Log.i("Gundam", model)
    mArray.add(Gundammodel, name)
}
~~~

# return, break

- Kotlin에서 label 사용 가능
    - @로 지정
- 중첩 되어 있을 때 어떤 것에 대한 것인지 명확히 알려줌
- break
    - for 안에 또 for가 있는 경우 break를 통해 바깥의 for를 종료하고 싶을 때 사용
- return 
    - break와 마찬가지로 함수 안에 함수가 있을 때, 특히 Lambda 식에서 return 하면 Lambda 식 밖의 함수가 return 되므로 지정 필요

~~~
mPlayer = mPlayer?.run {
    stop()
    return@run null
}
~~~

# == and ===

- == (2개)
    - 값이 같은지 비교
- !=
    - == 의 반대
- === (3개)
    - 같은 객체를 가리키면 true
- !===
    - === 의 반대

~~~
fun caller() {
    val a = "Hello"
    var b = "Hello+"
    val c = a

    b = b.filter { it != '+' } // b 에서 + 가 아닌것만 고른다 (람다식)
    println(b)

    if(a == b)
        println("a == b")
    if(a == c)
        println("a == c")

    if(a === b)
        println("a === b")
    if(a === c)
        println("a === c")
}
~~~

# let, run

- 객체에 대한 코드 블록 실행
    - 블록 실행 결과 return
- let
    - 블록 내에서 receiver 객체를 Lambda Expression의 it 으로 사용
- run
    - 블록 내에서 this이므로 생략 가능
~~~
// 만약 mPlayer null 이라면 아무것도 실행하지 않는다.
// null 이라면 it.stop 실행한다.

// 1 방식
if(mPlayer !== null) {
    mPlayer!!.stop()
    mPlayer = null
}

// 2 방식
mPlayer = mPlayer?.let { it: MediaPlayer
    it.stop()
    ^let null
    // 또는
    // return@let null
}

// 3 방식
mPlayer = mPlayer?.run { this: MediaPlayer
    stop()
    ^run null
    // 또는
    // return@run null
}

// 4 방식
mPlayer?.stop().let { mPlayer = null }

// 5 방식
mPlayer = mPlayer?.stop().let { null }
~~~

# apply, with, also

- apply
    - 여러 개 지정할 때 편리
    - receiver 객체 return
~~~
rectclerView.apply { this: RecyclerView
    setHasFixedSize(true)
    layoutManager = LinearLayoutManger(applicationContext)
    itemAnimator = DefaultItemAnimator()

    adapter = GundamAdapter()
}
~~~
- with (context 객체)
    - context 객체가 non-null일때 사용
- also
    - 코드 블록 내에서 receiver를 사용하지 않을 때 사용하면 좋음
    - 블록 내에서 receiver 객체를 Lambda Expression의 it으로 사용
    - receiver 객체 return

# 프로그래밍할때 유용한 사이트

- Android 가이드
    - https://developerandroid.com/guide/
- Kotlin 프로그래밍
    - https://developer.android.com/kotlin/
    - https://kotinlang.org/docs/reference/basic-syntax.html
- Kotlin Koans (문법 튜토리얼)
    - https://play.kotlinlang.org/koans/overview
- 프로그래밍 변수 이름 생성
    - https://www.curioustore.com

# 객체 지향 프로그래밍

- Object-Oriented Programming (OOP)
    - 각광받고 있는 컴퓨터 프로그래밍 방식중 하나
    - `실제 세계처럼 객체`이들이 존재하고 객체 사이에 메세지를 주고 받으면서 `상호작용하여 프로그램이 동작`
        - 객체는 `데이터 및 행동`을 같이 포함
    - 상속 관계 활용
        - 테스트를 마친 `완전한 클래스를 상속`받음
    - 재정의 `(overriding) 다형성 개념` 사용
        - 상속 받은 클래스에서 `메소드를 재정의`해서 원하는 작업 수행
    - 동적 바인딩
        - 실행 중에 적절한 클래스의 메소드를 수행하는 등의 다형성 개념 실현
        - 미리 만들어 놓은 클래스에서 나중에 상속 받은 클래스의 메소드 실행

## 상속성(inheritance)

- 클래스가 상위 클래스의 특성을 이어받을 수 있는 특성
- Modeling: 서로 연관 있는 클래스들을 계층적인 구조로 표현할 수 있으며, 자식 클래스로 갈수록 일반적인 것에서 특수한 것으로 이동

사람 - 교수
사람 - 학생 - 학부생
사람 - 학생 - 대학원생

- 실제 업무에서는 개발과 테스트를 거친 안정된 클래스를 사용하다가 수정하여야 할 때 그 클래스를 상속 받아서 필요한 부분만 수정

급여 - 수정 급여
Activity - MyActivity

## 안드로이드 프로그래밍

- Android(Google)에서 이미 만들어 놓은 Application Framework
    - 이미 상호 작용하면서 동작하도록 만들어져 있음
    - 필요한 클래스를 찾아서 원하는 메소드를 오버라이딩하여 프로그램 개발
- 뭘 배워야 하는가?
    - Android에서 어떤 클래스를 구현해 놨는지 알아야 함
    - 어떤 클래스를 상속 받을지 알아야 함
    - 그 클래스에서 어떤 메소드를 오버라이딩할지 알아야 함

## 프로젝트 

> MainActivity.class

~~~
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
~~~

setContentView 란 xml 파일의 텍스트 파일을 메모리로 읽어 들여서 객체로 만들어 화면에 보여주도록 해주는 메소드
`xml 을 읽어서 객체들로 만드는 과정을 인플레이션` 이라고 합니다.

setContentView() 메소드가 하는일은 화면의 content 부분을 지정시켜주는 메소드
R.layout.activity_main 파일을 가져와 보여줍니다.

R = res 를 의미합니다.

> /res/layout/activity_main.xml

해당 위치에 hello word 저장되어 있다는것을 확인할 수 있습니다. 

> /res/AndroidManifest.xml

응용프로그램이 하는일들을 상세하게 작성하는 곳
만약 작성하지 않고 실행하면 에러가 발생하게 됩니다.

~~~
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapplication">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
~~~

현재는 화면이 하나 존재하고 그것은 MainActivity 클래스입니다.
만약에 여러개의 화면이 존재한다면 계속해서 추가하면 됩니다.

# XML 및 Layout

- Layout은 UI 정의
    - View (Button 등) 및 View Group (Container, Layout) 배치
- XML (Extensible Markup Language) 형식으로 저장
    - 데이터 위주
    - 공유를 위한 데이터 + 구조 정의 (설정 파일 등)
    - Human Readable and Machine Readable (시람이 읽기 편하고 컴퓨터에서도 처리하기 편한다)
    - 텍스트 파일이어서 운영체제간 상호운용성 높음
    - Database의 테이블 구조 표현 용이
    - 데이터 전송에 유용함
        - 최근엔 데이터 전송을 위해서 JSON 많이 사용
    - User Interface의 Layout 정의에 많이 사용됨
         - Android, JavaFX (FXML), C# WPF (XAML)등

- 미리 정의된 Tag 없음
- Extensible 의 의미
    - 사용자가 Tag 정의 가능
- Prolog
    - <?xml version="10" encoding="UTF-8"?>
    - version 부분은 사용자가 변경하면 안됨
    - Prolog 위에 주석 <!----> 안됨
- 단 한 개의 Root Element 존재
    - Top level element
- 대소문자 구별
- Attribute: 시작 Tag에 포함
    - Name-Value pair (NV-pair)
    - Value 부분은 쌍따옴표 또는 따옴표로 묶어야 함
        - <TextView text="안녕하세요">

- Tag 정의시 같은 Tag를 다른 의미로 쓰게 되면 Name Confict 발생
    - 예를 들면, method를 다른 의미의 Tag로 사용
        - <method>Android<method>와 <method>ToString<method>
    - 앞에 prefix를 붙여 해결 가능
        - <p:method>Android</p:method>와 <n:method>ToString</n:method>
    - 문제는 계속됨, prefixeh Name Confict 가능

# 안드로이드

> example1 Project

- Activity
    - 눈에 보일때만 작동
    - 윈도우의 창 혹은 맥의 finder 라고 생각하면 된다.
    - Activity는 곧 화면이다` UI를 보야주고 해동까지 한다.

- Service
    - 눈에는 보이지 않지만 작동

- BroadcastReceiver
    - 알림의 상태를 알려주는 역할

- ContentProvider
    - 자료 제공자 사진을 가져오거나 주소록의 데이터를 가져오거나 그 밖에 어플리케이션에서 보관하는 데이터 정보를 가져올때 사용

- intent 
    - 다른 Activity를 시작할 수 있도록 도와줍니다. (명시적)
    - 다른 앱을 실행하도록 도와줍니다. (암시적)
    - 정보를 전달해주는 역할

> MainActivity.class

~~~
public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
~~~

> AndroidManifest.xml

~~~
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.example1">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity android:name=".MainActivity"></activity>

    </application>
</manifest>
~~~

activity 추가 후 run 을 실행합니다.

~~~
Error running 'MainActivity': The activity must be exported or contain an intent-filter
~~~

다음과 같은 에러가 발생합니다.

~~~
...
<activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"></action>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
...
~~~

값을 추가햐여 activiy 초기값을 지정해 줍니다.

## 생명주기 확인하기

> MainActivity.class

~~~
public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
}
~~~

프로젝트를 다시 실행 후 로그를 확인해보면

~~~
2020-01-21 17:46:31.415 6338-6338/? D/MainActivity: onCreate
2020-01-21 17:46:31.421 6338-6338/? D/MainActivity: onStart
2020-01-21 17:46:31.421 6338-6338/? D/MainActivity: onResume
~~~

그리고 프로젝트의 잠시 나가기 버튼을 클릭하면

~~~
2020-01-21 17:47:56.473 6338-6338/? D/MainActivity: onPause
2020-01-21 17:47:56.525 6338-6338/? D/MainActivity: onStop
~~~

나갔던 프로젝트를 다시 들어오면

~~~
2020-01-21 17:48:07.300 6338-6338/? D/MainActivity: onRestart
2020-01-21 17:48:07.301 6338-6338/? D/MainActivity: onStart
2020-01-21 17:48:07.302 6338-6338/? D/MainActivity: onResume
~~~

이렇게 프로젝트의 생며주기를 확인할 수 있습니다.

# BUTTON Itent 걸어보기

> example1 Project

~~~
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:text="CALL"
        android:id="@+id/btn_call"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        tools:ignore="MissingConstraints" />

</androidx.constraintlayout.widget.ConstraintLayout>
~~~

Button 간단하게 만들어 줍니다.

Click Itent를 걸어주기 위해서 MainActivity 위치에 View.OnClickListener 상속 받은 후 메소드를 오버라이드 합니다.

> MainActivity.class

~~~
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call 이벤트
        Button buttonCall = findViewById(R.id.btn_call);
        buttonCall.setOnClickListener(this);

        // Intent 이벤트
        Button buttonIndent = findViewById(R.id.btn_intent);
        buttonIndent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Call 이벤트
        Toast.makeText(this, "Click!!", Toast.LENGTH_SHORT).show();

        // Indent 이벤트
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_ALL_APPS);
        startActivity(intent);
    }
}
~~~

button.setOnClickListener(this); 이벤트를 부여함으로서 onClick 메소드가 실행됩니다.

## 명시적 Intent

> MainActivity.class

~~~
...
@Override
public void onClick(View v) {
    Intent intent = new Intent(MainActivity.this, CallActivity.class);
    intent.putExtra("intent-message", "jjunpro");
    startActivity(intent);
}
~~~

> CallActivity.class

~~~
public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        String message = getIntent().getStringExtra("intent-message");

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
~~~

> activity_call.xml

~~~
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/text1"
    android:text="Text1"
    tools:ignore="MissingConstraints" />
~~~

MainActivity 에서 전달받은 String 값을 출력하는것을 확인할 수 있습니다.

# View

## TextView

~~~
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
~~~

## ImageView

이미지 파일을 /res/drawable 폴더에 넣습니다.

그리고 표현하려는 위치 xml 이미지를 추가합니다.

> activity_main.xml 

~~~
<ImageView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:src="@drawable/testImg"
    android:scaleType="centerCrop"
    tools:ignore="MissingConstraints" />
~~~



# 출처
http://www.kocw.net/home/search/kemView.do?kemId=1319211