import { Link } from "react-router-dom";

function Home() {
    return (
        <div className="card shadow-sm">
            <div className="card-body p-5">
                <h1 className="mb-3">React 게시판 프로젝트</h1>

                <p className="text-secondary">
                    React Router, Axios, TanStack Query를 활용한 게시판
                    프로젝트입니다.
                </p>

                <Link className="btn btn-primary" to="/boards">
                    게시판 이동
                </Link>
            </div>
        </div>
    );
}

export default Home;
