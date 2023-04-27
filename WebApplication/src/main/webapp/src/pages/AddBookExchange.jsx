import {useNavigate} from "react-router-dom";
import * as Yup from "yup";
import React, {useState, useEffect} from "react";
import {FastField, Form, Formik} from "formik";
import InputField from "../custom-fields/InputField";
import './addBookExchange.css'
import {toast} from "react-toastify";
import axios from "axios";

const AddBookExchange = () => {
    const initialValues = {
        name: '',
        author: '',
        productImages: '',
        imagesPreview: '',
        description: '',

    }
    const navigate = useNavigate();
    const validationSchema = Yup.object().shape({
        name: Yup.string().required('Vui lòng nhập tên sách'),
        author: Yup.string().required('Vui lòng nhập tên tác giả'),
        productImages: Yup.mixed().required('Vui lòng chọn hình ảnh'),
        description: Yup.string().required('Vui lòng nhập mô tả'),
    });



    const saveBookExchange = async (values) => {
        const formData = new FormData();
        formData.append('name', values.name);
        formData.append('author', values.author);
        formData.append('productImages', values.productImages);
        formData.append('description', values.description);
       const response = await  axios.post('http://localhost:7070/api/books/post', formData,{
            headers: {
                'Content-Type': 'multipart/form-data'
            }
       });
         if (response.status === 200) {
                toast.success('Thêm sách thành công');
                navigate('/');
         } else {
                toast.error('Thêm sách thất bại');
         }
    }
    return (
        <div>
            <h2 className="page-header page-exchangeBook">
                Thêm sách
            </h2>
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card__body">
                            <Formik initialValues={initialValues}
                                    onSubmit={values => {
                                        saveBookExchange(values);
                                    }}
                                    validationSchema={validationSchema}

                            >
                                {({values, setFieldValue }) => (
                                    console.log(values),
                                    <Form>
                                        <div className="row">
                                            <div className="col-6">
                                                <div className="form-group">
                                                    <label htmlFor="name">Tên sách</label>
                                                    <FastField
                                                        component={InputField}
                                                        name="name"
                                                        type="text"
                                                        className="form-control"
                                                        placeholder="Nhập tên sách"
                                                    />


                                                </div>


                                            </div>
                                            <div className="col-6">
                                                <div className="form-group">
                                                    <label htmlFor="author">Tác giả</label>
                                                    <FastField
                                                        component={InputField}
                                                        name="author"
                                                        type="text"
                                                        className="form-control"
                                                        placeholder="Nhập tên tên tác giả"
                                                    />


                                                </div>
                                            </div>
                                            <div className="col-12">
                                                <div className="form-group">
                                                    <label htmlFor="productImages">Hình ảnh</label>
                                                    <input
                                                        name="productImages"
                                                        type="file"
                                                        className="form-control"
                                                        onChange={(event) => {
                                                           const file = event.target.files[0];
                                                           setFieldValue('productImages', file);
                                                           setFieldValue('imagesPreview', URL.createObjectURL(file));
                                                        }
                                                        }
                                                    />
                                                    {values.imagesPreview && (
                                                        <img src={values.imagesPreview} alt="Ảnh xem trước"
                                                             style={{height: '200px',margin: '10px'}}
                                                        />
                                                    )}

                                                </div>
                                            </div>
                                            <div className="col-12">
                                                <div className="form-group">
                                                    <label htmlFor="description">Mô tả</label>
                                                    <FastField
                                                        as="textarea"
                                                        name="description"
                                                        type="area"
                                                        className="form-control"
                                                        placeholder="Nhập mô tả"
                                                        style={{height: '200px'}}
                                                    />

                                                </div>
                                            </div>

                                        </div>
                                        <div className="row">
                                            <div className="col-12 btn-postBook">
                                                <button type="submit" className="btn btn-primary">Lưu</button>
                                            </div>
                                        </div>

                                    </Form>
                                )}
                            </Formik>

                        </div>
                    </div>
                </div>
            </div>
        </div>);
};

export default AddBookExchange;