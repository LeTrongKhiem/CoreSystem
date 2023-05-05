import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import ProductCard from "./ProductCard";

const BookList = (props) => {
    const backendUrl = "http://localhost:7070/";
    const navigate = useNavigate();
    const [range, setRange] = useState([...Array(props.totalPages).keys()]);
    useEffect(() => {
        const newRange = [...Array(props.totalPages).keys()];
        setRange(newRange);
    }, [props.totalPages]);
    const handleClick = (page) => {
        props.onChangePage(page-1);
    };
    return (
        <>
            {props.books.length === 0 ? <div className="error-not-fund-book"> Không tìm thấy</div> : (
                <div className="book-list">
                    {props.books.map((book) => (
                        <ProductCard
                            key={book.id}
                            id={book.id}
                            name={book.name}
                            author={book.author}
                            image01={backendUrl + book.productImagesUrl[0]}
                        />
                        // <div className="App" key={book.id} onClick={()=> {
                        //     navigate(`/book/${book.id}`);
                        // }
                        // }>
                        //     <div className="container">
                        //         <div className="row">
                        //             <div className="col-12">
                        //                 <div className="col-img">
                        //                     <img src={`${backendUrl}${book.productImagesUrl[0]}`} alt="logo"
                        //                          style={{
                        //                              width: "100%",
                        //                          }}
                        //                     />
                        //                 </div>
                        //                 <h1></h1>
                        //                 <div className="descriptive_text">
                        //                     <p>
                        //                         {book.name}
                        //                     </p>
                        //                 </div>
                        //                 <div className="descriptive_text">
                        //                     <p>
                        //                         {book.author}
                        //                     </p>
                        //                 </div>
                        //             </div>
                        //
                        //         </div>
                        //     </div>
                        // </div>
                    ))}

                </div>
            )}
            {
                props.totalPages > 1 ? (
                    <div className="table__pagination">
                        {
                            range.map((item, index) => (
                                <div
                                    key={index}
                                    className={`table__pagination-item ${
                                        props.currentPage === index  ? "active" : ""
                                    }`}
                                    onClick={() => handleClick(index + 1)}

                                >
                                    {index + 1}
                                </div>
                            ))
                        }
                    </div>
                ) : null
            }

        </>
    );
}
export default BookList;