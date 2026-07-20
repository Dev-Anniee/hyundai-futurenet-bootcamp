import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";

import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";
import Sidebar from "../components/Sidebar.jsx";
import ProtectedRoute from "./ProtectedRoute.jsx";

import Home from "../pages/Home.jsx";
import Login from "../pages/Login.jsx";
import Signup from "../pages/Signup.jsx";
import BoardList from "../pages/BoardList.jsx";
import BoardWrite from "../pages/BoardWrite.jsx";
import BoardDetail from "../pages/BoardDetail.jsx";
import BoardUpdate from "../pages/BoardUpdate.jsx";

function Layout() {
    return (
        <div className="d-flex flex-column min-vh-100">
            <Header />

            <div className="container flex-grow-1 py-4">
                <div className="row">
                    <aside className="col-md-3 mb-4">
                        <Sidebar />
                    </aside>

                    <main className="col-md-9">
                        <Outlet />
                    </main>
                </div>
            </div>

            <Footer />
        </div>
    );
}

function Router() {
    return (
        <BrowserRouter>
            <Routes>
                <Route element={<Layout />}>
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup" element={<Signup />} />

                    <Route path="/boards" element={<BoardList />} />
                    <Route path="/boards/:id" element={<BoardDetail />} />

                    {/* 로그인이 필요한 화면은 ProtectedRoute로 감쌉니다 */}
                    <Route
                        path="/boards/write"
                        element={
                            <ProtectedRoute>
                                <BoardWrite />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/boards/:id/update"
                        element={
                            <ProtectedRoute>
                                <BoardUpdate />
                            </ProtectedRoute>
                        }
                    />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default Router;
