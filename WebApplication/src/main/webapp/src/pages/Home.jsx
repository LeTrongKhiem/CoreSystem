import Search from "../components/Search";
import BookList from "../components/BookList";
import {useCallback, useEffect, useState} from "react";
import axios from "axios";
const Home = () => {
    const [searchTerm, setSearchTerm] = useState('');
    console.log(searchTerm)
    const [books, setBooks] = useState([]);
    console.log(books)
    const [sortBy, setSortBy] = useState('name');
    const [sortType, setSortType] = useState('asc');
    const [mostRecent, setMostRecent] = useState('desc');
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [pageSizes, setPageSizes] = useState(3);

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
  },[searchTerm, sortBy, sortType, mostRecent, currentPage, pageSizes])
    const handleSearch = (searchTerm) => {
        setSearchTerm(searchTerm);
    };
    const handlePageChange = useCallback((page) => {
        setCurrentPage(page); //trừ 1 vì page bắt đầu từ 0
    }, []);

    return (
        <div className="home">
            <Search searchTerm={searchTerm} handleSearch={handleSearch} />
            <BookList books={books}  currentPage={currentPage} totalPages={totalPages} onChangePage={handlePageChange}/>
        </div>
    );
}
export  default Home;