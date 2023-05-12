import React, {useEffect, useState} from 'react';
import './detailBook.css';
import {useParams} from "react-router-dom";
import axios from "axios";

const DetailBook=()=>{
    const [books,setBooks]=useState([])
    const [loading,setLoading]=useState(false)
    console.log(books)
    const API_URL = "http://localhost:7070/";
    const  params= useParams()
    const {id}=params

    useEffect(()=>{
        const getBook=async()=>{
            try{
                const res= await axios.get(`${API_URL}api/books/${id}`)
                setBooks(res.data)
                console.log(res.data)
                setLoading(true)
            }catch (err){
                console.log(err)
            }
        }
        getBook()

    },[id])
    if(!loading){
        return <div>loading...</div>
    }
    return(
        <div className="app">
                    <div className="details" key={books.id}>
                        <div className="big-img">
                            <img src={`${API_URL}${books.productImagesUrl[0]}`} alt=""/>
                        </div>

                        <div className="box">
                            <div className="row">
                                <h2>{books.name}</h2>
                            </div>
                            <p>Tác giả: {books.author}</p>
                            <p style={{
                                minHeight: "180px",
                            }}>{books.description}</p>
                            <button className="cart">Trao đổi</button>

                        </div>
                    </div>
        </div>
    );

}

export default DetailBook;