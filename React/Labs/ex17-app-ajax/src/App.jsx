import {useEffect , useState} from "react"
import axios from 'axios';


function App() {

  const [posts , setPosts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error , setError] = useState("");

  //useEffect 의존성 배열
  
  useEffect(()=>{
    axios.get("https://jsonplaceholder.typicode.com/posts?_limit=10")
         .then((response)=>{
              setPosts(response.data);
         })
         .catch((error)=> {
            console.log(error);
            setError("게시판 데이터를 가져오지 못했습니다");
         })
         .finally(()=>{
            setIsLoading(false);
         });
        
  },[]);

  if(isLoading){
    return <h2>로딩 중 .....</h2>
  }

  if(error){
     return <h2>{error}</h2>
  }

  return (
    <div>
          <h1>게시판 목록</h1>
          {
             posts.map((post) => (  //post 객체 1건을 받아서  {  }
                <div key={post.id}>
                    <h3>
                        {post.id}.{post.title}                      
                    </h3>
                    <p>
                       {post.body}                      
                    </p>
                </div>

             ))
           }
    </div>
  );
}

export default App
