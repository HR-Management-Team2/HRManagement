import React from "react";
import { Chart } from "react-google-charts";

export const data = [
  ["Year", "Sales", "Expenses"],
  ["2014", 1170, 460],
  ["2015", 660, 1120],
  ["2016", 1030, 540],
  ["2017", 1170, 460],
  ["2018", 660, 1120],
  ["2019", 1030, 540],
  ["2020", 1170, 460],
  ["2021", 660, 1120],
  ["2022", 1030, 540],
  ["2023", 1030, 540],
];

export const options = {
  title: "Company Performance",
  curveType: "function",
  legend: { position: "bottom" },
};

export default function ScatterChart() {
  return (
    <Chart
      chartType="ScatterChart"
      width="100%"
      height="300px"
      data={data}
      options={options}
    />
  );
}
