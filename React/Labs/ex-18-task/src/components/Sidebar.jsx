import { Link } from "react-router-dom";

function Sidebar() {
    return (
        <div className="card">
            <div className="card-header fw-bold">메뉴</div>

            <div className="list-group list-group-flush">
                <Link className="list-group-item" to="/">
                    홈
                </Link>

                <Link className="list-group-item" to="/boards">
                    게시글 목록
                </Link>

                <Link className="list-group-item" to="/boards/write">
                    게시글 등록
                </Link>
            </div>
        </div>
    );
}

export default Sidebar;
