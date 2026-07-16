import React from "react";
import { useState } from "react";

export function useInput(initValue, submitAction){    //useInput("hello",displayData)

    const [inputValue, setInputValue] = useState(initValue);

    const handleChange = (e) => {
        setInputValue(e.target.value);
    }

    const handleSubmit = () => {
        setInputValue('');
        submitAction(initValue);
    }

    return [inputValue , handleChange , handleSubmit];
}