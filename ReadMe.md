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

# 출처
http://www.kocw.net/home/search/kemView.do?kemId=1319211