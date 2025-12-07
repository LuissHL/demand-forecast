from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import uvicorn
import numpy as np

app = FastAPI()

class ForecastRequest(BaseModel):
    productId: int
    history: List[int]

class ForecastResponse(BaseModel):
    forecast: float

@app.post("/predict", response_model=ForecastResponse)
def predict(req: ForecastRequest):

    history = req.history

    if len(history) == 0:
        return ForecastResponse(forecast=0.0)

    # Previsão simples usando média móvel
    forecast = float(np.mean(history))

    return ForecastResponse(forecast=forecast)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)
