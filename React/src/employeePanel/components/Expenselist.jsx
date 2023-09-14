
import { useEffect, useState } from "react";
import PageLayoutEmployee from "../components/PageLayoutEmployee";
import { Button, Table } from "antd";
import axios from "axios";
import { EditOutlined, DeleteOutlined  } from "@ant-design/icons";
import AddExpensesModal from "../components/AddExpensesModal";
import dayjs from 'dayjs';


const Expenselist = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [expenses, setExpenses] = useState([]);
  const [initialValues, setInitialValues] = useState([]);
  const [search, setSearch] = useState("");

  const buttons = [
    {
      key: "addExpenses",
      text: "Add Expenses",
      type: "primary",
      onClick: () => {
        setIsModalOpen(true);
        setInitialValues(null);
      },
    },
  ];

  const onOkAddModel = (values) => {
    setIsModalOpen(false);
    console.log(values);

    if (initialValues) {
      axios
        .put(`http://localhost:5000/expenses/${initialValues.id}`, {
          ...values
        })
        .then((res) => {
          setExpenses((prevState) => {
            return prevState.map((u) => {
              if (res.data.id === u.id) {
                return {
                  ...res.data,
                };
              } 
              return u;
            });
          });
        });
    } else {
      const newExpenses = {
        expenseType: values.expenseType,
        description: values.description,
        amount: values.amount,
        currency: values.currency,
        replyDate: values.replyDate,
        status:values.status,
        dateOfRequest: values.dateOfRequest
      };
      axios.post("http://localhost:5000/expenses", newExpenses).then((res) => {
        setExpenses((prevState) => [
          ...prevState,
          {
            ...res.data, 
          },
        ]);
      });
    }
  };

  const onCancelAddModel = () => {
    setIsModalOpen(false);
  };

  const onClickEdit = (row) => {
    setIsModalOpen(true);
    row.establish=dayjs(row.establish)
    setInitialValues(row);
  };
  const onSearch = (value) => {
    setSearch(value);
  };



  // const { Option } = Select; 

  const onStatusChange = (expenseId, status) => {
    if (!expenseId || !status) {
      console.error("Invalid Expense ID");
      return;
    }
  
    axios
      .put(`http://localhost:5000/expenses/${expenseId}`, { status })
      .then((res) => {
        setExpenses((prevState) =>
          prevState.map((expense) => {
            if (expense.id === expenseId) {
              return { ...expense, status };
            } else {
              return expense;
            }
          })
        );
      })
      .catch((error) => {
        console.error("Update status error:", error);
      });
  };
  

  const onDeleteExpense = (expenseId) => {
    if (!expenseId) {
      console.error("Invalid Expense ID");
      return;
    }
  
    axios
      .delete(`http://localhost:5000/expenses/${expenseId}`)
      .then((res) => {
        setExpenses((prevState) =>
          prevState.filter((expense) => expense.id !== expenseId)
        );
      })
      .catch((error) => {
        console.error("Delete error:", error);
      });
  };
  
  
  
  const columns = [
    {
      title: "Expense Type",
      dataIndex: "expenseType",
      key: "expenseType",
    },
    {
      title: "Description",
      dataIndex: "description",
      key: "description",
    },
    {
      title: "Amount",
      dataIndex: "amount",
      key: "amount",
    },
    {
      title: "Currency",
      dataIndex: "currency",
      key: "currency",
    },
    {
      title: "Reply Date",
      dataIndex: "replyDate",
      key: "replyDate",
    },
    {
      title: "Status",
      dataIndex: "status",
      key: "status",
    },
    {
      dataIndex: "id",
      key: "id",
      render: (cell, row) => {
        return (
          <>
            <Button
              type="primary"
              shape="circle"
              icon={<EditOutlined />}
              onClick={() => onClickEdit(row)}
            />
            <Button
              type="danger"
              shape="circle"
              icon={<DeleteOutlined />}
              onClick={() => onDeleteExpense(row.id)} 
            />
          </>
        );
      },
      width: 100,
    },
  ];

  useEffect(() => {
    axios.get("http://localhost:5000/expenses").then((res) => {
      setExpenses(res.data);
    });
  }, []);

  return (
    <PageLayoutEmployee buttons={buttons} onSearch={onSearch}>
      <Table
  dataSource={expenses.filter((expense) => {
    return (
      expense && 
      expense.expenseType || 
      expense.description.includes(search)
    );
  })}
  columns={columns}
  rowKey="id"
/>

      {isModalOpen && (
        <AddExpensesModal
          isModalOpen={isModalOpen}
          onOk={onOkAddModel}
          onCancel={onCancelAddModel}
          initialValues={initialValues}
          expenses={expenses}
          onStatusChange={onStatusChange}
        />
      )}
    </PageLayoutEmployee>
  );
};

export default Expenselist;
