import React from 'react'
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import RootLayout from './layouts/RootLayout'

import Home from './pages/Home'
import About from './pages/About'
import Products from './pages/Products'
import ProductDetail from './pages/ProductDetail'
import NotFound from './pages/NotFound'
//createBrowserRouter()는 **라우트(경로와 컴포넌트 연결)**를 “객체 배열”로 정의해서
//브라우저 히스토리 기반 Router 인스턴스를 만드는 함수입
const router = createBrowserRouter([
  {
    element: <RootLayout />,
    children: [
      { index: true, element: <Home /> },
      { path: 'about', element: <About /> },
      { path: 'products', element: <Products /> },
      { path: 'products/:id', element: <ProductDetail /> },
      { path: '*', element: <NotFound /> }
    ]
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
)
/*
코드	의미
element: <RootLayout />	                       공통 레이아웃(헤더, 푸터, Outlet 등 포함)
children	                                     RootLayout 안에서 바뀌는 페이지들
{ index: true, element: <Home /> }	           기본 페이지(/)
{ path: 'about', element: <About /> }          /about 페이지
{ path: '*', element: <NotFound /> }	         매칭 안 되는 모든 경로 (404)

즉,
RootLayout 안의 <Outlet /> 자리에 각 페이지(Home, About)가 들어가게 됩니다.
*/
