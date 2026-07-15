import { Routes, Route } from 'react-router-dom'
import RootLayout from './layouts/RootLayout'
import Home from './pages/Home'
import About from './pages/About'
import Products from './pages/Products'
import ProductDetail from './pages/ProductDetail'
import NotFound from './pages/NotFound'

export default function App() {
  return (
    <Routes>
      {/* 레이아웃 라우트: 항상 공통으로 보일 영역은 RootLayout 안에 배치 */}
      <Route element={<RootLayout />}>
            <Route index element={<Home />} />
            <Route path="about" element={<About />} />
            <Route path="products" element={<Products />} />
            <Route path="products/:id" element={<ProductDetail />} />
            {/* 404 */}
            <Route path="*" element={<NotFound />} />
      </Route>
    </Routes>
  )
}
