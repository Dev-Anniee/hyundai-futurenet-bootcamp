import './App.css'
import {useState , useReducer} from 'react'

  //자바의 열거형 (enum)
  //DB에서는 코드 테이블 
  //javascript (react) 01 출금 , 02 입금
  //json 객체 설계

  const ACTION_TYPES = {   //객체를 통해서 열거타입 설계
    deposit:'deposit',
    withdraw:'withdraw'
  }

  //복잡한 논리를 처리하는 함수 (비지니스 요구사항 처리 함수)

  //dispatch({type:"deposit",payload:number})}
  const reducer = (state, action) =>{  //값 , 행위정보
      //개발자마음
      switch(action.type){
        case ACTION_TYPES.deposit : return state + action.payload; //입금
        case ACTION_TYPES.withdraw : return state - action.payload;//출금
      }
  }

  /*
     reducer : 로직을 통해서 state(값을) 변경하는 논리
     action  : 행위에 따라서 논리가 적용 (입금 , 출금 , 계좌생성 , 송금)
     dispatch: 은행직원에게 요구 

     행위
     입금 , 출금
     onClick > dispatch() 부르고 > reducer 호출

  */
function App() {

  const [number,setNumber] = useState(0);
  const [money,dispatch] = useReducer(reducer,0);  //const reducer = (state, action) 처리함수
  //dispatch 에 parameter 전달 객체형태로
  //dispatch({type:"deposit",payload:number})}
  return (
      <div className='App'>
          <h3>KOSA 은행</h3>
          <p>잔액: {money}</p>

          <hr />
          <input type="number" value={number} onChange={(e) => {setNumber(parseInt(e.target.value))}} step="1000" />
          <hr />
          <button onClick={()=>{dispatch({type:"deposit",payload:number})}}>reducer 호출(예금)</button>
          <hr />
          <button onClick={()=>{dispatch({type:"withdraw",payload:number})}}>reducer 호출(출금)</button>
      </div>
  )
}

export default App
