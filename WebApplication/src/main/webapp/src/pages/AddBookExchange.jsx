import {useNavigate} from "react-router-dom";
import * as Yup from "yup";
import React, {useState, useEffect} from "react";
import {FastField, Form, Formik} from "formik";
import InputField from "../custom-fields/InputField";
import './addBookExchange.css'
const AddBookExchange = () => {
    const initialValues = {
        name: '',
        priceOld: 0,
        priceCurrent: 0,
        description: '',
        category_id: [],
        categoryNames: [],
        brand_id: '',
        brandName: '',
        consumer: 0,
        cotton: 0,
        made_in: '',
        sale_off: '',
        type: '',
        form: '',


    }




    const saveProduct = async (values) => {

    }
    return (
        <div>
        <h2 className="page-header">
           Thêm sách
        </h2>
        <div className="row">
            <div className="col-12">
                <div className="card">
                    <div className="card__body">
                        <Formik initialValues={initialValues}>
                            {({}) => (
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
                                                    htmlFor="name"
                                                />


                                            </div>

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