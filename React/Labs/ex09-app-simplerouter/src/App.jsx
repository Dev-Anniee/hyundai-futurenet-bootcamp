import './App.css'
import {BrowserRouter , Routes , Route , Link} from "react-router-dom"

//라우팅 > UI > 컴포넌트 > Home.jsx , About.jsx > 함수
function Home(){
  return (
    <div>
        <h3>Home Page</h3>
        <p>Welcome to homepage</p>
    </div>
  )
}

function About(){
  return (
    <div>
        <h3>About Page</h3>
        <p>Welcome to About</p>
    </div>
  )
}

function Contact(){
  return (
    <div>
        <h3>Contact Page</h3>
        <p>Welcome to Contact</p>
    </div>
  )
}

function App() {
 

  return (
      <BrowserRouter>
        <div>
            <nav>
              <ul>
                <li>
                   <Link to="/">Home</Link>
                </li>  
                <li>
                   <Link to="/about">About</Link>
                </li>  
                <li>
                   <Link to="/contact">Contact</Link>
                </li>  
              </ul>  
            </nav>          
        </div>
        <Routes>
            <Route path='/'        element={<Home />} />    
            <Route path='/about'   element={<About />} />  
            <Route path='/contact' element={<Contact />} />      
        </Routes>
      </BrowserRouter>
  )
}

export default App
