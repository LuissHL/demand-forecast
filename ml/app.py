from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import numpy as np
from sklearn.linear_model import LinearRegression

app = FastAPI()

# ===== DTO =====

class ForecastRequest(BaseModel):
    productId: int
    history: List[int]

class ForecastResponse(BaseModel):
    forecast: float

# ===== ENDPOINT REAL DE ML =====

@app.post("/predict", response_model=ForecastResponse)
def predict(request: ForecastRequest):

    history = np.array(request.history)

    # Se não tiver dados suficientes
    if len(history) < 2:
        return {"forecast": float(history.mean()) if len(history) > 0 else 0.0}

    # Criando X baseado no tempo (dias)
    X = np.arange(len(history)).reshape(-1, 1)
    y = history

    # Treinando modelo REAL
    model = LinearRegression()
    model.fit(X, y)

    # Prevendo próximo dia
    next_day = np.array([[len(history)]])
    prediction = model.predict(next_day)[0]

    return {"forecast": float(prediction)}
