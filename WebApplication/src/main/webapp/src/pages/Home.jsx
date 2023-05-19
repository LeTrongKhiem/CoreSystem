import Search from "../components/Search";
import BookList from "../components/BookList";
import {useCallback, useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";
import {selectIsLoggedIn} from "../redux/slice/authSlice";
import {toast} from "react-toastify";

const Home = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [books, setBooks] = useState([]);
    console.log(books)
    const [sortBy, setSortBy] = useState('name');
    const [sortType, setSortType] = useState('asc');
    const [mostRecent, setMostRecent] = useState('desc');
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [pageSizes, setPageSizes] = useState(3);
    const isLoggin = useSelector(selectIsLoggedIn);
    const navigate = useNavigate()

    useEffect(() => {
        const getBooks = async () => {
            const response = await axios.get('http://localhost:7070/api/books/', {
                params: {
                    search: searchTerm,
                    sortBy: sortBy,
                    sortType: sortType,
                    mostRecent: mostRecent,
                    pageSize: pageSizes,
                    page: currentPage
                }
            });
            if (response.status === 200) {
                setBooks(response.data.results);
                setTotalPages(response.data.totalPages);
            }
            console.log(response.data)
        }
        getBooks();
    }, [searchTerm, sortBy, sortType, mostRecent, currentPage, pageSizes])
    const handleSearch = (searchTerm) => {
        setSearchTerm(searchTerm);
    };
    const handlePageChange = useCallback((page) => {
        setCurrentPage(page); //trừ 1 vì page bắt đầu từ 0
    }, []);
    const goToPostBook = () => {
        if (isLoggin) {
            navigate('/post-book')
        } else {
            toast.error('Vui lòng đăng nhập để thực hiện chức năng này');
            navigate('/login')
        }
    }
    return (
        <div className="home">
            <div className="buttons">
                {isLoggin ? (   <button className="logout">Đăng xuất</button>) : (  <button className="login">Đăng nhập</button>)}

                <button className="post" onClick={goToPostBook}>Đăng sách</button>
            </div>
            <Search searchTerm={searchTerm} handleSearch={handleSearch}/>
            <BookList books={books} currentPage={currentPage} totalPages={totalPages} onChangePage={handlePageChange}/>
        </div>
    );
}
export default Home;